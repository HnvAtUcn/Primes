
public class Main {
	static final int NVALUES = 1000000;
	static long[] knownValues = {1l, 2l, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L};
	static long[] testValues = {31L, 33L, 37L, 39L};
	static int index = 10;
	static long[] thePrimes = new long[NVALUES];
	static CalcThread theThread = new CalcThread();

	public static void main(String[] args) {
		System.out.println("Starting");
		thePrimes[0] = 2;
		thePrimes[1] = 3;
		thePrimes[2] = 5;
		thePrimes[3] = 7;
		thePrimes[4] = 11;
		thePrimes[5] = 13;
		thePrimes[6] = 17;
		thePrimes[7] = 19;
		thePrimes[8] = 23;
		thePrimes[9] = 29;
		
		theThread.run();
	}

}

class CalcThread extends Thread {

	@Override
	public void run() {
		while (Main.index < Main.NVALUES)
		{	
			for (int i=0;i<4;i++)
			{
				long candidate = Main.testValues[i];
				boolean go_on = true;
				boolean is_prime = true;
				for (int j = 1;(go_on && is_prime);j++)
				{
					if (j==2)
						j++; // Skipping 5
					long divisor = Main.thePrimes[j];
					is_prime = ((candidate % divisor) == 0) ? false : true;
					go_on = (divisor*divisor > candidate) ? false : true;
				}
				if (is_prime) 
				{
					Main.thePrimes[Main.index++] = candidate;
					if (Main.index == Main.NVALUES) {
						break;
					}
				}
				Main.testValues[i] += 10;	
			}
		}
		System.out.println("Done");
	}

}
