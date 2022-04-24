package analysis.acidirican.sorting;

/**
 * 
 * @author AhmetCengizhan Dirican
 * @version 1.0
 *
 */
public class Progress extends Thread {
	private boolean goOn = true;

	@Override
	public void run() {
		while (goOn) {
			System.out.print(".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopProgress() {
		goOn = false;
	}

	public static Progress startProgress() {
		Progress progress = new Progress();
		progress.start();
		return progress;
	}

}
