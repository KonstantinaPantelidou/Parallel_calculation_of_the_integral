import java.util.concurrent.locks.ReentrantLock;

public class PiGroupThread extends Thread {

	private long myStart;
	private long myStop;
	private double sum;
	private double step;
	private static volatile double pi = 0;
	ReentrantLock lock;

	public PiGroupThread(int myId, int numThreads, long size, double step, ReentrantLock lock) {

		this.step = step;
		this.lock = lock;
		myStart = myId * (size / numThreads);
		myStop = myStart + (size / numThreads);
		if (myId == (numThreads - 1))
			myStop = size;
		sum = 0;
		// for debugging
		// System.out.println("Thread ID: " + myId + "\tMy Start: " + myStart +
		// "\tMyStop: " + myStop + "\n");

	}

	public void run() {

		/* do computation */
		for (long i = myStart; i < myStop; i++) {
			double x = ((double) i + 0.5) * step;
			sum += 4.0 / (1.0 + x * x);
		}

		/* Lock critical section / Critical Value (pi) */
		lock.lock();
		pi += sum;
		lock.unlock();
	}

	public static double getPi() {
		return pi;
	}

}
