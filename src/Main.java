
public class Main {
	static int N = 950023;			//prime
	static double lamda = 0.5;		//average		//0<l<1
	static double epsilon = 0.2;	//median		//0<e<0.5
	static int k = 2;				//k'th wise

	static long startTime;
	static long endTime;
	static long runTime;


	static Ams ams = new Ams(N, lamda, epsilon, k);	//est. F2
	static Ams2 ams2 = new Ams2();					//real F2
	
	public static void main(String[] args) {

		//1=attack
		//2=normal
		startTime = System.currentTimeMillis();	//System.nanoTime(); //
		stream(2);
		int result = ams.median();

		endTime = System.currentTimeMillis();	//System.nanoTime(); //
		runTime = Math.abs(endTime - startTime);
		
		
		System.out.println("time: "+runTime+" (ms)");
		System.out.println("F2:  "+ams2.getF2());
		System.out.println("F2~: "+result);
		System.out.println("DIF: "+Math.abs(result-ams2.getF2()));

		System.out.println("Done.");
	}

	//stream ...
	public static void stream(int n)
	{
		int r;
		if (n==1){
			//attack
			for (int i=0; i<5000; i++)
			{
				r = random(0, N-2);
				ams.add(r);
				ams2.add(r);
				
			}
			for (int i=0; i<5000; i++)
			{
				ams.add(N-1);
				ams2.add(N-1);
			}
		}
		
		else if (n==2){
			//no attack
			for (int i=0; i<10000; i++)
			{
				r = random(1, N-1);
				ams.add(r);
				ams2.add(r);
			}
		}

	}
	
	private static int random(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));		
	}
}
