import java.util.Arrays;

public class Ams {

	private int N;		//prime
	private int t;		//t hypothesis's
	private int l;		//median
	private int[] h;	//the k-wise's coefficients
	private int k;		//number of coefficients
	private int x[];	//holds t x's


	public Ams(int N, double lamda, double epsilon, int k) {
		this.N = N;
		this.t = (int) ( (4*4)/(lamda*lamda) );
		this.l = (int) (24 * Math.log(1/epsilon));
		this.k = k;
		this.x = new int[t*l];

		//generate l*t random of k numbers
		h = new int[k*(l*t)];
		for (int i=0; i<(l*t); i++) {
			h[k*i] = random(1, N-1);			//first k is from 1
			for (int j=1; j<k; j++)
				h[k*i + j] = random(0, N-1);
		}
	}

	public void add(int index) {
		for (int i=0; i<(t*l); i++)
			x[i] += h(index, Arrays.copyOfRange(h, k*i, k*i+k));
	}

	public int avg(int j) {
		double z = 0;
		for (int i=j*t; i<j*t+t; i++)
			z += (x[i]*x[i])/t;
		System.out.println("- "+z);
		return (int)z;
	}

	public int median() {
		int[] z = new int[l];
		for (int i=0; i<l; i++){
			System.out.print( (i==l/2-1?"-":"") );
			z[i] = avg(i);
		}
		Arrays.sort(z);
		return z[l/2];
	}

	//K_wise
	private int h(int index, int[] c)
	{
		//build it
		for (int i=0; i<N; i++)
		{
			//k-wise calculation
			for (int j=0; j<k; j++)
				h[i] = h[i] + c[j]*i^(k-j);
			h[i] = h[i] % N;

			//<0,...,k> -> <0, 1>
			h[i] = h[i] % 2;

			//<0, 1> -> <-1,1>
			h[i] = h[i]*2 - 1;

			//short the function to index
			if (i == index)
				break;
		}
		return h[index];
	}

	private int random(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));		
	}
}
