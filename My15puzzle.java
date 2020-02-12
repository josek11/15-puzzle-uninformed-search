import java.util.*;
import java.io.*;

public class My15puzzle
{	
	//verificar se ha solucao
	public static Boolean isSolvable(int[] vi, int[] vf){	
		int i, contai=0, blanki=0, contaf=0, blankf=0,j;
		for(i=0;i<vi.length;i++){
			for(j=i+1;j<vi.length;j++){
				if(vi[i]>vi[j] && vi[j]!=0)
					contai++;
				else if(vi[j]==0)
					blanki=j;
			}
			
		}

		for(i=0;i<vf.length;i++){
			for(j=i+1;j<vf.length;j++){
				if(vf[i]>vf[j] && vf[j]!=0)
					contaf++;
				else if(vf[j]==0)
					blankf=j;
			}
			
		}
		Boolean b1 =((contai%2==0)==((blanki/4)%2==1));
		Boolean b2 =((contaf%2==0)==((blankf/4)%2==1));
		if(b1 == b2)
			return true;
		else 
			return false;
	}

	public static void main(String[] args){	
		Scanner stdin = new Scanner(System.in);
		int startconf[] = new int[16];
		int endconf[] = new int[16];
		int i,j;

		System.out.println("Insira a configuracao inicial");
		//ler config inicial em forma de array
		for(i=0;i<16;i++){
			startconf[i] = stdin.nextInt();
		} 

		System.out.println("Insira a configuracao final");
		//ler config final em forma de array;
		for(i=0;i<16;i++){
			endconf[i] = stdin.nextInt();
		}
		
		Boolean check = isSolvable(startconf, endconf);
		if(!check){
			System.out.println("Not Solvable");
			return;
		}

		else{
			//transformar arrays em matriz
			int myiconfig[][] = new int[4][4];
			int myfconfig[][] = new int[4][4];

			int h=0;
			for(i=0;i<4;i++){
				for(j=0;j<4;j++){
					myiconfig[i][j] = startconf[h];
					
					h++;
				}
			}
			h=0;
			for(i=0;i<4;i++){
				for(j=0;j<4;j++){
					myfconfig[i][j] = endconf[h];
					h++;
				}
			}

			System.out.println("Escolha o algoritmo de pesquisa:");
			System.out.println("1 - BFS");
			System.out.println("2 - DFS");
			System.out.println("3 - IDFS");
			System.out.println("4 - AStar");
			System.out.println("5 - Greedy");

			int escolha = stdin.nextInt();
			String a="";
			double start = 0.0;
			double used_memory=0.0;
			System.out.println();
			int escolha2=0;

			switch(escolha){
				case 1:
					System.out.println("-----Resultados de BFS:-----");				
					System.out.println();
					start = new Date().getTime();
					a =Algoritmos.bfs(myiconfig, myfconfig);					
					break;
				case 2: 
					System.out.println("-----Resultados de DFS:-----");				
					System.out.println();
					start = new Date().getTime();
					a =Algoritmos.dfs(myiconfig, myfconfig);					
					break;
				case 3:
					System.out.println("-----Resultados de IDFS:-----");			
					System.out.println();
					start = new Date().getTime();
					a =Algoritmos.idfs(myiconfig, myfconfig, 0);
					break;
				case 4:
					System.out.println("Escolha a Heuristica:");
					System.out.println("  (1)- Numero de pecas fora do lugar");
					System.out.println("  (2)- Distancia de Manhattan");
					escolha2 = stdin.nextInt();
					System.out.println();
					System.out.println("-----Resultados de AStar:-----");
					start = new Date().getTime();
					a =Algoritmos.aStar(myiconfig, myfconfig, escolha2);					
					break;
				case 5:
					System.out.println("Escolha a Heuristica:");
					System.out.println("  (1)- Numero de pecas fora do lugar");
					System.out.println("  (2)- Distancia de Manhattan");
					escolha2 = stdin.nextInt();
					System.out.println();
					System.out.println("-----Resultados de Greedy:------");
					start = new Date().getTime();
					a =Algoritmos.greedy(myiconfig,myfconfig, escolha2);
					break;
			}
			
			if(a=="Solution not found")
				System.out.println("nope");
			else 
			 	used_memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			 	double end = new Date().getTime();
				System.out.println("Path: " + a);
				System.out.println("Execution time: " + ((end-start)/1000)+"s");
				System.out.printf("Used memory: %.5fMB\n",(used_memory/(1024*1024)));
				
		}		

	}
}