package firstlab;
import java.util.*;
public class HelloWorlld {
	
	public static void main(String args[]) {
		long startTime = System.nanoTime();
		HelloWorlld HL=new HelloWorlld();
		//HL.compulsory();
		//for(int k=1; k<=100; ++k) {
			int k=17;
			String hihi[]= {"10", "1000", ""};
			hihi[2]=Integer.toString(k);
			HL.homework(hihi, startTime);
			
		//}
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
		
		int k=int_args.get(2);
		int a=int_args.get(0);
		int b=int_args.get(1);
		
		for(int nr=a; nr<=b; ++nr) 
			if(k_reductibil(nr,k)==k){
				K_List.add(nr);
		}
		if(K_List.isEmpty())
			return ;
		System.out.println("Starting printing "+k+"-reductible numbers>");
		for(Integer it: K_List) {
			System.out.print(it+", ");
		}
		System.out.println();
		long ET=System.nanoTime(); long dur=ET-ST;
		System.out.println("The running time (in nano seconds) is= "+ dur);
	}
	public short k_reductibil(int value, int maybe) {
		
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
				return (short)maybe;
			
			value=aux;
			if(nr_it>100)
				break;
		}
		
		return (short)value;
	}
}