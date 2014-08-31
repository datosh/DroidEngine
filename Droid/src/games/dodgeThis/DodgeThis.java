package games.dodgeThis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import ai.Repeat;
import ai.Wander;
import physics.collision.ColliderBox;
import util.Point;
import games.Game;
import gfx.animation.Animator;

public class DodgeThis extends Game {
	private static final long serialVersionUID = -3973103538665918880L;
	
	private DodgePlayer player;
	private List<Enemy> enemys;
	private List<PowerUp> powerUps;
	private Bomb bomb;
	private Timer enemyTimer;
	private Timer speedTimer;
	private Timer powerTimer;
	private Timer bombTimer;
	private Random random;
	private long played;
	private int speed;
	
	public DodgeThis(String name) {
		super(name, 1368);
		
		random = new Random();
}

	@Override
	public void init() {
		/* INIT PLAYER OBJECT */
		player = new DodgePlayer(this, INPUT, 200, 300);
		player.setCollider(new ColliderBox(0, 0, 50, 25, player));
		player.setRigidbody(new PlayerRigidbody(INPUT, player));
		player.getRigidbody().setSpeed(220);
		String[] playerLocs = {"assets/dodge_this/player.png"};
		long[] playerDelays = {1_000_000};
		player.setAnimator(new Animator(player, playerLocs, playerDelays));
		
		/* INIT SPACE FOR ENEMYS AND INITIAL ENEMY */
		enemys = new ArrayList<Enemy>(25);
		speed = 125;
		addEnemy();
		
		/* INIT SPACE FOR POWER UPS */
		powerUps = new ArrayList<PowerUp>(5);
		
		/* TIMER FOR SCOREBOARD <=> TIME PLAYED */
		played = 0;
		
		/* SET TIMER FOR MONSTER SPAWNS */
		ActionListener enemySpawner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addEnemy();
			}
		};
		enemyTimer = new Timer(2000, enemySpawner);
		enemyTimer.setRepeats(true);
		enemyTimer.start();
		
		/* SET TIMER FOR SPEED INCREASE */
		ActionListener speedIncreaser = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				speed += 24;
			}
		};
		speedTimer = new Timer(3000, speedIncreaser);
		speedTimer.setRepeats(true);
		speedTimer.start();
		
		/* SET TIMER FOR POWER UPS */
		ActionListener powerUpSpawner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPowerUp();
			}
		};
		powerTimer = new Timer(8000, powerUpSpawner);
		powerTimer.setRepeats(true);
		powerTimer.start();
		
		/* SET TIMER FOR BOMB */
		ActionListener bombSpawner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createBomb();
			}
		};
		bombTimer = new Timer(4000, bombSpawner);
		bombTimer.setRepeats(true);
		bombTimer.start();
	}

	@Override
	public void tick(long delta) {
		// If game is already over do not update anymore
		if(state.equals(STATE.GAMEOVER)) return;
		
		played += delta;
		
		player.update(delta);

		/* UPDATE BOMB */
		if(bomb != null) {
			bomb.update(delta);
			if(player.getCollider().overlaps(bomb.getCollider())) {
				/* DELTE EVERY OTHER ENEMY */
				Iterator<Enemy> it = enemys.iterator();
				boolean other = false;
				while(it.hasNext()) {
					it.next();
					other = !other;
					if(other) {
						it.remove();
						continue;
					}
				}
				
				/* DELTE BOMB */
				bomb = null;
			}
		}
		
		/* UPDATE POWER UPS */
		Iterator<PowerUp> itp = powerUps.iterator();
		while(itp.hasNext()) {
			PowerUp current = itp.next();
			
			current.update(delta);
			
			if(!current.isAlive()) {
				itp.remove();
				continue;
			}
			
			if(player.getCollider().overlaps(current.getCollider())) {
				player.setImmune(true);
				itp.remove();
				continue;
			}
		}
		
		/* UPDATE ENEMYS */
		Iterator<Enemy> it = enemys.iterator();
		while(it.hasNext()) {
			Enemy current = it.next();
			current.getRigidbody().setSpeed(speed);
			current.update(delta);
			if(player.getCollider().overlaps(current.getCollider())) {
				if(player.isImmune()) {
					player.setImmune(false);
				} else {
					System.err.println("YOU COLLIDED WITH AN ENEMY!");
					player.getCollider().setColor(Color.RED);
					current.getCollider().setColor(Color.RED);
					state = STATE.GAMEOVER;
					return;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(50, 70, 250));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		player.draw(g);
		
		if(bomb != null) {
			bomb.draw(g);
		}
		
		Iterator<PowerUp> itp = powerUps.iterator();
		while(itp.hasNext()) {
			itp.next().draw(g);
		}
		
		Iterator<Enemy> it = enemys.iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans", Font.BOLD, 16));
		String scoreboard = "You have survived for " + (int)(played / 1_000_000) + " seconds!";
		g.drawString(scoreboard, getWidth() / 5, 20);
		
		//If the game is marked gameover, draw one last time and then exit
		if(state.equals(STATE.GAMEOVER)) {
			String gameOverMsg = "YOU COLLIDED! GAME OVER!";
			g.setColor(Color.RED);
			g.setFont(new Font("Comic Sans", Font.BOLD, 24));
			g.drawString(gameOverMsg, getWidth()/2 - gameOverMsg.length()/2*12, getHeight()/2);
			this.stop();
			return;
		}
	}

	@Override
	public void cleanUp() {
		enemyTimer.stop();
		speedTimer.stop();
		powerTimer.stop();
		bombTimer.stop();
	}
	
	public static void main(String[] args) {
		new DodgeThis("Dodge This!").start();
	}
	
	private void addEnemy() {
		Enemy enemy = new Enemy(this, random.nextInt(this.getWidth()), random.nextInt(this.getHeight()));
		enemy.setCollider(new ColliderBox(0, 0, 50, 50, enemy));
		enemy.setRoutine(new Repeat(new Wander(new Point(0, 0), new Point(this.getWidth(), this.getHeight()))));
		enemy.setRigidbody(new EnemyRigidbody(null, enemy));
		String[] locs = {"assets/dodge_this/enemy.png", "assets/dodge_this/bomb.png", "assets/dodge_this/shield.png"};
		long[] delays = {1_000_000, 1_000_000, 1_000_000};
		enemy.setAnimator(new Animator(enemy, locs, delays));
		enemys.add(enemy);
	}
	
	private void addPowerUp() {
		PowerUp pu = new PowerUp(this, random.nextInt(this.getWidth()), random.nextInt(this.getHeight()));
		pu.setCollider(new ColliderBox(random.nextInt(this.getWidth()), random.nextInt(this.getHeight()), 25, 25, pu));
		pu.getCollider().setColor(Color.PINK);
		String[] locs = {"assets/dodge_this/shield.png"};
		long[] delays = {1_000_000};
		pu.setAnimator(new Animator(pu, locs, delays));
		powerUps.add(pu);
	}

	private void createBomb() {
		bomb = new Bomb(this, random.nextInt(this.getWidth()), random.nextInt(this.getHeight()));
		bomb.setCollider(new ColliderBox(random.nextInt(this.getWidth()), random.nextInt(this.getHeight()), 25, 25, bomb));
		bomb.getCollider().setColor(Color.RED);
		String[] locs = {"assets/dodge_this/bomb.png"};
		long[] delays = {1_000_000};
		bomb.setAnimator(new Animator(bomb, locs, delays));
	}
}
