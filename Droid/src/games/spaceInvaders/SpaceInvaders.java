package games.spaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

import entities.GameObject;
import physics.collision.ColliderBox;
import util.Point;
import games.Game;
import gfx.animation.Animator;

public class SpaceInvaders extends Game {
	private static final long serialVersionUID = -6646613736996774008L;
	
	SpacePlayer player;
	Timer shootCD;
	List<Projectile> projectiles;
	List<Enemy> enemys;
	
	
	public SpaceInvaders(String name, int width) {
		super(name, width);
		
	}
	
	@Override
	public void init() {
		
		//INIT PLAYER
		player = new SpacePlayer(this, INPUT, this.getWidth()/2, this.getHeight() - 127);
		player.setCollider(new ColliderBox(9, 87, 110, 40, player));
		player.setRigidbody(new PlayerRigidbody(INPUT, player));
		player.getRigidbody().setSpeed(122);
		String[] locs = {"assets/space_invaders/player.png"};
		long[] delays = {1_000_000};
		player.setAnimator(new Animator(player, locs, delays));
		
		//PROJECTILES
		projectiles = new ArrayList<Projectile>();
		
		//CD COUNTER
		shootCD = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DONE!");
			}
		});
		shootCD.start();
		shootCD.setRepeats(false);
		
		//ENEMYS
		enemys = new ArrayList<Enemy>();
		addEnemy(150, 150);
		
	}

	@Override
	public void tick(long delta) {
		// TODO Auto-generated method stub
		player.update(delta);
		
		// TODO PLACE THIS IN PLAYER CLASS AND MAKE SPACE CAST X 
		if(INPUT._SPACE) {
			if(shootCD.isRunning()) {
				System.out.println("SHOOT STILL ON CD!");
			} else {
				addProjectile(player.getX(), player.getY());
				shootCD.restart();
			}
		}
			
		Iterator<Projectile> itProjectiles;
		Iterator<Enemy> itEnemies;
		
		//CHECK FOR COLLISIONS
		itProjectiles = projectiles.iterator();
		while(itProjectiles.hasNext()) {
			Projectile currProj = itProjectiles.next();
			itEnemies = enemys.iterator();
			while(itEnemies.hasNext()) {
				Enemy currEne = itEnemies.next();
				if(currProj.getCollider().overlaps(currEne.getCollider()) && currEne.isAlive() && currProj.isAlive()) {
					currEne.setAlive(false);
					currProj.setAlive(false);
				}
			}
		}
		
		//UPDATE PROJECTILES
		itProjectiles = projectiles.iterator();
		while(itProjectiles.hasNext()) {
			Projectile current = itProjectiles.next();
			current.update(delta);
			if(!current.isAlive()) {
				itProjectiles.remove();
			}
		}
		
		//UPDATE ENEMYS
		itEnemies = enemys.iterator();
		while(itEnemies.hasNext()) {
			Enemy current = itEnemies.next();
			current.update(delta);
			if(!current.isAlive()) {
				itEnemies.remove();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		player.draw(g);
		
		//PROJECTILES
		Iterator<Projectile> itProjectiles = projectiles.iterator();
		while(itProjectiles.hasNext()) {
			itProjectiles.next().draw(g);
		}
		System.out.println("Currently " + projectiles.size() + " Projectiles on the Board!");
		
		//ENEMIES
		Iterator<Enemy> itEnemies = enemys.iterator();
		while(itEnemies.hasNext()) {
			Enemy current = itEnemies.next();
			current.draw(g);
		}
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		shootCD.stop();
	}
	
	private void addProjectile(double x, double y) {
		Projectile projectile = new Projectile(this);
		projectile.setPosition(new Point(x + 58, y + 75));
		String[] locs = {"assets/space_invaders/projectile.png"};
		long[] delays = {1_000_000};
		projectile.setAnimator(new Animator(projectile, locs, delays));
		projectile.setCollider(new ColliderBox(0, 0, 10, 20, projectile));
		projectiles.add(projectile);
	}
	
	private void addEnemy(double x, double y) {
		Enemy enemy = new Enemy(this);
		enemy.setPosition(new Point(x, y));
		String[] locs = {"assets/space_invaders/enemy_1.png", "assets/space_invaders/enemy_2.png"};
		long[] delays = {500_000, 500_000};
		enemy.setAnimator(new Animator(enemy, locs, delays));
		enemy.setCollider(new ColliderBox(0, 0, 128, 128, enemy));
		enemys.add(enemy);
	}
	
	public static void main(String[] args) {
		new SpaceInvaders("Space Invaders", 800).start();
	}
	
}
