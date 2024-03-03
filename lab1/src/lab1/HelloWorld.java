package lab1;
import java.util.*;
public class HelloWorld {
	
	public static void main(String args[]) {
		long startTime = System.nanoTime();
		HelloWorld HL=new HelloWorld();
		//HL.compulsory();
		/*for(int k=1; k<=100; ++k) {
			String hihi[]= {"10", "1000", ""};
			hihi[2]=Integer.toString(k);
			HL.homework(hihi, startTime);
		}	*/
		//HL.homework(args, startTime);
		HL.bonus(4);
	}
	public void compulsory() {
		String[] languages={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
		System.out.println(languages);
		int n = (int) (Math.random() * 1_000_000);
		int res=(n*3+0b10101+0xFF)*6;
		
		int CC=res%9;
		if (CC==0) {
			CC=9;
		}
		res=cifra_control(res);
		System.out.println("Willy-nilly, this semester I will learn " + languages[res]);	
	}
	public static int cifra_control(int k) {
		while (k>9) {
			int sumcif=0;
			int cpy=k;
			while (cpy>0) {
				sumcif+=(cpy%10);
				cpy/=10;
			}
			k=sumcif;
		}
		return k;
	}
	public void homework(String args[], long ST) {
		Vector<Integer> int_args=new Vector<Integer>();
		Vector<Integer> K_List=new Vector<Integer>();
		for(String it: args) {
			int number=Integer.valueOf(it);
			int_args.add(number);
		}
		if(int_args.isEmpty()) return ;
		
		int k=int_args.get(2);
		int a=int_args.get(0);
		int b=int_args.get(1);
		if(k==0) {
			//setting a default input
			k=1; a=100; b=200;
		}
		
		for(int nr=a; nr<=b; ++nr) 
			if(k_reductibil(nr,k)==true){
				K_List.add(nr);
		}
		
		if(!K_List.isEmpty()) {
			System.out.println("Starting printing "+k+"-reductible numbers>");
			for(Integer it: K_List) {
				System.out.print(it+", ");
			}
			System.out.println();
		}
		long ET=System.nanoTime(); long dur=ET-ST;
		System.out.println("The running time (in nano seconds) is= "+ dur);
	}
	public boolean k_reductibil(int value, int maybe) {
		if (value==maybe)
			return true;
		int sum_cif=0;
		int nr_it=0;
		while(value>9) {
			int aux=value;
			nr_it++;
			while(aux>0) {
				sum_cif+=(aux%10)*(aux%10);
				aux/=10;
			}
			aux=sum_cif;
			if(maybe==aux)
				return true;
			
			value=aux;
			if(nr_it>100)
				break;
		}
		
		return false;
	}
	public void bonus(int N) {
		if(N<=3) {
			System.out.println("Not enough points for Wheel Graph");
			return ;
		}
		
		wheel_graph W=new wheel_graph(N);
		int howmanycycles=W.print_all_cycles();
		int predicted=N*N-3*N+3;
		if(howmanycycles!=predicted) {
			System.out.println("Looks like I made a mistake>");
			System.out.println("Predicted is "+predicted+" and my answer is "+howmanycycles);
		}
		else {
			System.out.println("My answer is correct (N^2-3*N+3)> "+howmanycycles);
		}
	}
	public class wheel_graph{
		int N;
		int [][] adj_mat;
		public wheel_graph(int K) {
			N=K;
			adj_mat=new int [N+5][N+5];
			
			for (int point=1; point<N; point++) {
				int pre=(point==1)?(N-1):(point-1);
				int post=(point==N-1)?(1):(point+1);
				adj_mat[point][pre]=adj_mat[pre][point]=1;
				adj_mat[point][post]=adj_mat[post][point]=1;
				adj_mat[point][N]=adj_mat[N][point]=1;
				adj_mat[point][0]=3;
			}

			adj_mat[N][0]=N-1;
		}
		
		public void print_mat_text() {

			for(int node=1; node<=N; ++node) {
				System.out.print("The node="+node+" is connected to "+adj_mat[node][0]+" nodes: ");
				for(int nxt=1; nxt<=N; ++nxt)
					if(adj_mat[node][nxt]==1) {
						System.out.print(nxt+" ");
					}
				System.out.println();
			}
		}
		public int print_all_cycles() {
			int ans=0;
			for(int K=1; K<N; ++K) {
				ans+=print_cycles_of_K_slices(K);
			}
			return ans;
		}
		
		public int print_cycles_of_K_slices(int K) {
			int no_cycles=0;
			
			for(int first_slice=1; first_slice<N; ++first_slice) {
				if(K==N-1 && first_slice>1)
					break;
				
				boolean List_of_nodes[]= new boolean[N+3];
				Vector<Integer> order_of_nodes= new Vector<Integer>(2*N+3);
				order_of_nodes.clear();
				Arrays.fill(List_of_nodes, 0,N,false);
				for(int ind_slice=first_slice; ind_slice<first_slice+K; ++ind_slice) {
					List_of_nodes[(ind_slice%(N-1)==0)?(N-1):(ind_slice%(N-1))]=true;
					List_of_nodes[((ind_slice+1)%(N-1)==0)?(N-1):((ind_slice+1)%(N-1))]=true;
					order_of_nodes.add((ind_slice%(N-1)==0)?(N-1):(ind_slice%(N-1)));
					order_of_nodes.add(((ind_slice+1)%(N-1)==0)?(N-1):((ind_slice+1)%(N-1)));
				}
				
				no_cycles++;
				if(K<N-1) {
					List_of_nodes[N]=true;
					order_of_nodes.add(N);
				}
				System.out.println(K+": the "+ first_slice+"-th cycle has the following nodes:");
				
				for(int node:order_of_nodes)
					if(List_of_nodes[node]==true) {
						List_of_nodes[node]=false;
						System.out.print(node+";");
					}
				System.out.println();
				
			}
			return no_cycles;
		}
	}
}