import java.util.Vector;


public class Ams2 {

	//frequency vector
	//f[i] is the freq of i (=ip)
	private Vector<Integer> f;
	private int N;		//prime
	private int[][] h;	//the k-wise 's
	private int t;		//t hypothesis's
	private int[] z;	//array of z's for average
	private double x;

	

	public Ams2(){//int N, int t, int median) {
		this.f = new Vector<Integer>();
		//this.m = 0;
		//this.N = N;
		//this.t = t;
		this.x = 0;

		//build k-wise ind.
		//build();
	}
	
	//
	public int getF2() {return F(f);}	//E[z] = E[x^2] = F2
	
	public void build()
	{
	
		//create t hypothesis's
		h = new int[t][];
		for (int i=0; i<t; i++)
			h[i] = new K_wise(N, 4).getH();

		//calc t x's and mark as z
		z = new int[t];
		for (int i=0; i<t; i++) {
			z[i] = calc_x(i);
			z[i] *= z[i];	//z=x^2
		}
		
		//V[z] = 2*F2^2
		//int VZ = 2*F2*F2;
		//System.out.println("VZ: "+VZ);
		
		int F2_ = E(z);
		System.out.println("F2_: "+F2_);
	}

	//sigma[ h(i)*f(i) ]
	private int calc_x(int t) {
		int x = 0;
		for (int i=0; i<N-1; i++)
			x += h[t][i]*f.get(i);
		return x;
	}

	//mean
	private int E(int[] arr) {
		int avg = 0;
		for (int i=0; i<arr.length; i++)
			avg += arr[i];
		avg /= arr.length;
		return avg;
	}

	//F
	private int F(Vector<Integer> v) {
		int sum = 0;
		for (int i=0; i<v.size(); i++)
			sum += v.get(i) * v.get(i);
		return sum;
	}

	public void add(int index) {
		if (index < f.size()) {
			Integer temp = f.get(index);
			temp++;
			f.set(index, temp);
		} else {
			int zeros = index - f.size();
			for (int i=0; i<zeros; i++)
				f.add(0);
			f.add(1);
		}
	}

	public void printF()
	{
		for (int i=0; i<f.size(); i++)
			System.out.println(i+". "+f.get(i));
	}
	

}
