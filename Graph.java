package assignment4Graph;

public class Graph {
	
	boolean[][] adjacency;
	int nbNodes;
	
	public Graph (int nb){
		this.nbNodes = nb;
		this.adjacency = new boolean [nb][nb];
		for (int i = 0; i < nb; i++){
			for (int j = 0; j < nb; j++){
				this.adjacency[i][j] = false;
			}
		}
	}
	
	public void addEdge (int i, int j){
		this.adjacency[i][j] = true;
		this.adjacency[j][i] = true;
	}
	
	public void removeEdge (int i, int j){
		this.adjacency[i][j] = false;
		this.adjacency[j][i] = false;
	}
	
	public int nbEdges(){
		int counter = 0; int indep = 0;
		for (int i=0; i<this.nbNodes; i++)
		{
			for (int j=0; j<this.nbNodes; j++)
			{
				if((this.adjacency[i][j]==true)&&(i!=j))
					counter++;
				else if ((this.adjacency[i][j]==true)&&(i==j))
					indep++;
			}
		}
		counter = counter/2;
		counter = counter+indep;
		return counter;
	}
	
	public boolean cycle(int start){
		int edge = 0;
		for (int j=0; j<this.nbNodes; j++)
		{
			if (this.adjacency[start][j] == true)
			{
				if (j==start) continue;
				else edge++;
			}
		}
		if (edge < 2)
			return false; 
		else
		{
			int stack[] = new int[this.nbNodes]; 
			int watchlist[] = new int[this.nbNodes];
			for(int i=0; i<this.nbNodes; i++)
			{
				stack[i] = 1000;
				watchlist[i] = 1000;
			}
			return depthCycle(stack, watchlist, start, start);	
		}
	}
	
	public int shortestPath(int start, int end){
		if(this.adjacency[start][end]==true)
			return 1;
		else if ((tryWalk(start).length==0)||(tryWalk(end).length==0))
			return this.nbNodes+1;
		else if ((start==end)&&(this.adjacency[start][end]==false)&&(this.cycle(start)==false))
			return this.nbNodes+1;
		else if ((start==end)&&(this.adjacency[start][end]==false)&&(this.cycle(start)==true))
		{
			int stack[] = new int[this.nbNodes]; 
			int watchlist[] = new int[this.nbNodes];
			for(int i=0; i<this.nbNodes; i++)
			{
				stack[i] = 1000;
				watchlist[i] = 1000;
			}
			return depthCycleF(stack, watchlist, start, end);
		}
		
		int n = this.nbEdges();
		int queue[][] = new int[n][2]; 
		int watching[][] = new int[n][2]; 
		for(int i=0; i<this.nbEdges(); i++)
		{
			for(int j=0; j<2; j++)
			{
				queue[i][j] = 1000;
				watching[i][j] = 1000;
			}
		}
		int a[] = {start, start};
		pushB(queue, a);
		return breadthS(queue, watching, start, start, end);
	}
	
	public int[] tryWalk(int start)
	{
		int edge = 0;
		for (int i=0; i<this.nbNodes; i++)
		{
			if (this.adjacency[start][i] == true)
			{
				if (i==start) continue;
				edge++;
			}
		}
		int children[] = new int[edge];
		int p = 0;
		for (int i=0; i<this.nbNodes; i++)
		{
			if (this.adjacency[start][i] == true)
			{
				if (i==start) continue;
				else
				{
					children[p] = i;
					p++;
				}
			}
		}
		return children;
	}
	
	public boolean exist (int arr[], int k)
	{
		for (int i=0; i<arr.length; i++)
		{
			if (arr[i] == k)
				return true;
		}
		return false;
	}
	
	public boolean exist (int arr[][], int k[])
	{
		for (int i=0; i<arr.length; i++)
		{
			if(arrayEquals(arr[i], k)==true)
				return true;
		}
		return false;
	}
	
	public void push (int arr[], int a)
	{
		if(arr[arr.length-1] == 1000)
		{
			arr[arr.length-1] = a; 
			return;
		}
		for(int i=1; i<arr.length; i++)
		{
			if(arr[i] != 1000)
			{
				arr[i-1] = a; 
				return;
			}
		}
	}
	
	public void pushB (int arr[][], int a[])
	{
		int x[] = {1000,1000};

		for(int i=arr.length-1; i>=0; i--)
		{
			if(arrayEquals(arr[i],x)==true)
			{
				arr[i] = a; 
				return;
			}
		}
	}
	
	public void pop (int arr[])
	{
		for(int i=0; i<arr.length; i++)
		{
			if(arr[i] != 1000)
			{
				arr[i] = 1000; 
				return;
			}
		}
	}
	
	public int[] pull (int arr[][], int w[][])
	{
		int[] temp = arr[arr.length-1];
		pushB(w, temp);
		for(int i=arr.length-1; i>0; i--)
		{
			arr[i] = arr[i-1];
		}
		int x[] = {1000,1000};
		arr[0] = x;
		return temp;
	}
	
	public boolean depthCycle(int arr[], int w[], int x, int start)
	{
		push(arr, x); push(w, x);
		if((this.adjacency[get(arr)][start]==true) &&(greaterThan3(arr)==true)) 
			return true;
		while(isNumber(arr, 0)==false)
		{
			for (int i=0; i<tryWalk(get(arr)).length; i++)
			{
				if(tryWalk(get(arr)).length<2)
					break;
				else if(exist(w, tryWalk(get(arr))[i]) == false)
					return depthCycle(arr, w, tryWalk(get(arr))[i], start);
			}
			pop(arr);
		}
		return false;
	}
	
	public int depthCycleF(int arr[], int w[], int x, int start)
	{
		push(arr, x); push(w, x);
		if((this.adjacency[get(arr)][start]==true) &&(greaterThan3(arr)==true)) 
			return count(arr);
		while(isNumber(arr, 0)==false)
		{
			for (int i=0; i<tryWalk(get(arr)).length; i++)
			{
				if(tryWalk(get(arr)).length<2)
					break;
				else if(exist(w, tryWalk(get(arr))[i]) == false)
					return depthCycleF(arr, w, tryWalk(get(arr))[i], start);
			}
			pop(arr);
		}
		return 1000;
	}
	
	public int breadthS (int arr[][], int w[][], int sub, int start, int end)
	{
		if(isNumber(arr, 0)==true)
			return this.nbNodes+1;
		if((this.adjacency[pull(arr,w)[0]][end] == true)&&(end!=get(w)[1]))
		{       
			int counter = 1; int i = 0; int t=get(w)[0]; 
			while(i<w.length)
			{
				if(w[i][0]==t)
				{
					if(w[i][1]==start)
					{
						if(start==end)
							return counter+=2;
						else
							return ++counter;	
					}
					else
					{
						t = getSmallest(w, w[i][0]);  
						if(t==start) 
						{
							if(start==end)
								return counter+=2;
							else
								return ++counter;
						}
						counter++;
					}
				}
				i++;
			}
		}
		for (int i=0; i<tryWalk(sub).length; i++)
		{
			int n[] = {tryWalk(sub)[i], sub};
			int n2[] = {sub, tryWalk(sub)[i]};
			if((exist(w, n)==false)&&(exist(w, n2)==false)&&(exist(arr, n)==false)&&(exist(arr, n2)==false))
			{
				pushB(arr, n);
			}
		}
		return breadthS(arr, w, arr[arr.length-1][0], start, end);
	}
	
	public boolean isNumber (int arr[], int n)
	{
		int counter=0;
		for (int i=0; i<arr.length; i++)
		{
			if(arr[i] != 1000)
				counter++;
		}
		if (counter == n)
			return true;
		else
			return false;
	}
	
	public boolean isNumber (int arr[][], int n)
	{
		int counter=0;
		int x[] = {1000,1000};
		for (int i=0; i<arr.length; i++)
		{
			if(arrayEquals(arr[i], x)==false)
				counter++;
		}
		if (counter == n)
			return true;
		else
			return false;
	}
	
	public int[] get (int w[][])
	{
		int x[] = {1000,1000};
		for (int i=0; i<w.length; i++)
		{
			if(arrayEquals(w[i], x)==false)
				return w[i];
		}
		return null;
	}
	
	public int getSmallest (int w[][], int a)
	{
		for (int j=w.length-1; j>=0; j--)
		{
			if(w[j][0]==a)
				return w[j][1];
		}
		return 0;
	}
	
	public int get (int w[])
	{
		for (int i=0; i<w.length; i++)
		{
			if(w[i]!=1000)
				return w[i];
		}
		return 0;
	}
	
	public boolean arrayEquals (int a[], int b[])
	{
		for (int i=0; i<a.length; i++)
		{
			if(a[i] != b[i])
				return false;
		}
		return true;
	}
	
	public boolean greaterThan3 (int arr[])
	{
		int counter=0;
		for (int i=0; i<arr.length; i++)
		{
			if(arr[i] != 1000)
				counter++;
		}
		if(counter>=3)
			return true;
		else
			return false;
	}
	
	public boolean greaterThan3 (int w[][])
	{
		int counter=0;
		for (int i=0; i<w.length; i++)
		{
			int x[] = {1000,1000};
			if(arrayEquals(w[i], x)==false)
				counter++;
		}
		if(counter>=3)
			return true;
		else
			return false;
	}
	
	public int count (int a[])
	{
		int counter=0;
		for (int i=0; i<a.length; i++)
		{
			if(a[i]!=1000)
				counter++;
		}
		return counter;
	}
}
