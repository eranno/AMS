
public class K_wise {

	private int m; 		//prime
	private int k; 		//k'th wise
	private int[] c;	//coefficients
	private int[] h;	//k-wise
	
	public K_wise(int m, int k)
	{
		this(m, k, null);
	}
	
	public K_wise(int m, int k, int[] c)
	{
		h = new int[m];
		
		//if coefficients were not selected, choose them randomly
		if (c == null) {
			c = new int[k];
			c[0] = random(1,m-1);
			for (int i=1; i<k; i++)
				c[i] = random(0,m-1);
		}
		this.c = c;
		this.k = k;
		this.m = m;

		//build it
		h();
	}
	
	public int[] getH() {return h;}
	
	private void h()
	{
		int x;
		
		//build it
		for (int i=0; i<m; i++)
		{
			for (int j=0; j<k; j++){
				x = (int)Math.pow(i,(k-j));
				h[i] = h[i] + c[j]*x;
			}
			h[i] %= m;

			
			
			//% FIXED TO <-1, 1> %//
			
			//<0,...,k> -> <0, 1>
			//h[i] = h[i] % 2;
			h[i] = h[i] & 1;	//bitwise
			
			//<0, 1> -> <-1,1>
			h[i] = h[i]*2 - 1;
			
			//%                  %//
		}
	}
	
	private int random(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));		
	}
	
	
	/*
//matlab:

%% k-wise
m=7;	%prime
k=4;	%k-wise

for i=1:k
	h(i) = randi([0, 9]);
end

%% 4-wise
%a=2;
%b=2;
%c=2;
%d=2;
%h=[a,b,c,d];

for i=0:m-1
	for j=1:length(h)
		ans(i+1) = ans(i+1) + h(j)*i^(length(h)-j);
	end
	ans(i+1) = mod(ans(i+1),m);
end
	 */
}
