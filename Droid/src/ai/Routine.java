package ai;

import util.Board;
import entities.Droid;

public abstract class Routine {
	
	public enum RoutineState {
		SUCCESS, 
		FAILURE,
		RUNNING
	}
	
	protected RoutineState state;
	
	protected Routine() {
		
	}
	
	public void start() {
		System.out.println("Start Routine: " + this.getClass().getSimpleName());
		this.state = RoutineState.RUNNING;
	}
	
	public abstract void reset();
	
	public abstract void act(long delta, Droid droid, Board board);
	
	protected void succeed() {
		System.out.println("Routine: " + this.getClass().getSimpleName() + " SUCCEEDED!");
		this.state = RoutineState.SUCCESS;
	}
	
	protected void fail() {
		System.out.println("Routine: " + this.getClass().getSimpleName() + " FAILED!");
		this.state = RoutineState.FAILURE;
	}
	
	public boolean isSuccess() {
		return state.equals(RoutineState.SUCCESS);
	}
	
	public boolean isFailure() {
		return state.equals(RoutineState.FAILURE);
	}
	
	public boolean isRunning() {
		return state.equals(RoutineState.RUNNING);
	}
	
	public RoutineState getState() {
		return state;
	}

}
