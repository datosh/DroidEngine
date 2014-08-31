package games;

import input.InputHandler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -5926662799507913257L;

	public final boolean DEBUG = true;
	
	public static final InputHandler INPUT = new InputHandler();
	public enum STATE {
		RUNNING, GAMEOVER
	}
	
	protected String name;
	protected STATE state;
	
	/* VARS FOR FRAME RATE */
	private final int MAX_FPS = 60;
	private final long SLEEP_TIME = 3;
	private final double NS_PER_RENDER = 1_000_000_000D / MAX_FPS;
	private final Color defaultBGColor = new Color(255, 255, 255);
	
	private boolean running = false;
	private JFrame frame;
	
	public Game(String name) {
		this(name, 512);
	}
	
	public Game(String name, int width) {
		int height = width / 16 * 9;
		
		Dimension size = new Dimension(width, height);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setPreferredSize(size);
		
		this.setBackground(defaultBGColor);
		this.setIgnoreRepaint(true);
		
		this.addKeyListener(INPUT);
		this.addMouseListener(INPUT);
		
		this.name = name;
		frame = new JFrame(name);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(this);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//Input will instantly go to us
		this.requestFocus();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public STATE getState() {
		return state;
	}
	
	/**
	 * Allocate Space for ObjectPool and similar stuff here
	 */
	public abstract void init();
	
	/**
	 * Start the game by creating a new thread. 
	 */
	public void start() {
		running = true;
		state = STATE.RUNNING;
		new Thread(this).start();
	}
	
	/**
	 * Tell the game thread to stop after the next iteration
	 */
	public void stop() {
		running = false;
	}
	
	/**
	 * Calls tick() as often as possible, while sleeping for
	 * SLEEP_TIME nano seconds. The render() function is called
	 * every 1/MAXFPS seconds. 
	 */
	public void run() {
		int ticks = 0;		//Counter for ticks per second
		int frames = 0;		//Counter for frames per second
		double delta = 0;	//Used to determine if we need to render
		long deltaTick = 0;	//Time between two ticks => physics
		
		long lastTime = System.nanoTime();					//Time since last tick
		long lastFullSecond = System.currentTimeMillis();	//After one second benchmarks are printed out
		
		init();
		
		while(running) {
			//Calculate time since last tick, and update draw counter (delta)
			long now = System.nanoTime();
			deltaTick = now - lastTime;
			delta += (now - lastTime) / NS_PER_RENDER;
			lastTime = System.nanoTime();

			//Update the state of the game
			ticks++;
			tick(deltaTick / 1000);	//Pass delta as µs
			
			//As long as frames are need to be drawn, draw them
			while(delta >= 1) {
				BufferStrategy bs = getBufferStrategy();
				if(bs == null) {
					createBufferStrategy(2);
				} else {
					Graphics g = bs.getDrawGraphics();
					g.setColor(defaultBGColor);
					g.fillRect(0, 0, this.getWidth(), this.getHeight());
					frames++;
					render(g);
					g.dispose();
					bs.show();
				}
				delta--;
			}
			
			//Sleep for some time so other thread do get some time as well
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			
			
			//If one second has passed print benchmarks and reset counter
			if(System.currentTimeMillis() - lastFullSecond >= 1000) {
				lastFullSecond += 1000;
				frame.setTitle(name + " " + ticks + " ticks, " + frames + " frames");
				ticks = 0;
				frames = 0;
			}
		}
		//Give game to execute some final commands
		cleanUp();
	}
	
	/**
	 * Update the state of the game
	 * @param delta time in µs since last call
	 */
	public abstract void tick(long delta);
	
	/**
	 * Draw the current state of the game
	 * @param g draw onto this object
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Is called once after the thread is terminated via
	 * the stop() function. Shall be used to do some final
	 * operations. 
	 */
	public abstract void cleanUp();
}
