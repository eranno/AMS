import java.util.Arrays;

public class Ams {

	private int N;		//prime
	private int t;		//t hypothesis's
	private int l;		//median
	private int[] c;	//the k-wise's coefficients
	private int k;		//number of coefficients
	private int x[];	//holds t x's


	public Ams(int N, double lamda, double epsilon, int k) {
		this.N = N;
		this.t = (int) ( (4*4)/(lamda*lamda) );
		this.l = (int) (24 * Math.log(1/epsilon));
		this.k = k;
		this.x = new int[t*l];

		//generate l*t random of k numbers
		c = new int[k*(l*t)];
		for (int i=0; i<(l*t); i++) {
			//h[k*i] = random(1, N-1);//first k is from 1??
			for (int j=0; j<k; j++){
				c[k*i + j] = random(0, N-1);
				//System.out.print(", "+c[k*i + j]);
			}
			//System.out.println();
		}
	}

	public void add(int index) {
		for (int i=0; i<(t*l); i++)
			x[i] += h(index, Arrays.copyOfRange(c, k*i, k*i+k));
	}

	public int avg(int j) {
		double z = 0;
		for (int i=j*t; i<j*t+t; i++)
			z += (x[i]*x[i])/t;
		//System.out.println("- "+z);
		return (int)z;
	}

	public int median() {
		int[] z = new int[l];
		for (int i=0; i<l; i++){
			z[i] = avg(i);
		}
		Arrays.sort(z);
		return z[l/2];
	}

	//K_wise
	private int h(int i, int[] c)
	{
		int x = 1;
		int h = 0;

		//k-wise calculation
		for (int j=0; j<k; j++){
			//x = (int)Math.pow(i,(k-j));	//slow
			x = x*i;						//super fast! no need to calc exponent over and over and over...
			h = h + c[j]*x;
		}
		h = h % N;

		//<0,...,k> -> <0, 1>
		//h[i] = h[i] % 2;	//modulus
		h = h & 1;	//bitwise

		//<0, 1> -> <-1,1>
		h = h*2 - 1;

		return h;
	}

	private int random(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));		
	}
}
