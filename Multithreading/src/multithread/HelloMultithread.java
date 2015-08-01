package multithread;

public class HelloMultithread {

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			ThreadRunnable a = new ThreadRunnable(i);
			Thread t = new Thread(a);
			t.start();
		}
	}

}
