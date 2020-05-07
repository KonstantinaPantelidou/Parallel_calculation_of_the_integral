import java.util.concurrent.locks.ReentrantLock;


class Pi_Computation extends Thread
{
	private double sum;
	private long myStart;
    private long myStop;
	private double myStep;
	private static double pi = 0; //to oliko sum
	private static ReentrantLock lock = new ReentrantLock(true);

	// constructor
	public Pi_Computation(int myId, int numThreads,long size, double step)
	{
		myStep = step;
		myStart = myId * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) myStop = size;
		sum=0;
	}
	// thread code
	public void run()
	{
		for (long i=myStart; i < myStop; ++i) {
            double x = ((double)i+0.5)*myStep;
            sum += 4.0/(1.0+x*x);
        }
		lock.lock();
        pi += sum;
        lock.unlock();
	}

	public static double getPi() {
		return pi;
	}
	
	
}