
public class Main {
	public static void main(String[] args) {

		int N = 101;			//prime
		double lamda = 1;		//average		//0<l<16?
		double epsilon = 0.2;	//median		//0<e<1
		int k = 4;				//k'th wise
		
		Ams ams = new Ams(N, lamda, epsilon, k);
		stream(ams);
		System.out.println(ams.median());


		System.out.println("Done.");
	}

	//stream ...
	public static void stream(Ams ams)
	{
		//attack
//		for (int i=0; i<1000; i++)
//		{
//			ams.add(random(1, 100));
//		}
//		for (int i=0; i<1000; i++)
//		{
//			ams.add(0);
//		}
		
		//no attack
		for (int i=0; i<2000; i++)
		{
			ams.add(random(0, 100));
		}

	}
	
	private static int random(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));		
	}
}
