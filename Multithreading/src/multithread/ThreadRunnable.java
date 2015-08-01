package multithread;

public class ThreadRunnable implements Runnable {

	private int Id;

	public ThreadRunnable(int i) {
		this.Id = i;
	}

	@Override
	public void run() {
		System.out.println("Hello World from Thread " + this.Id);
	}

}