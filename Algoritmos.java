import java.util.*;

class myComparator implements Comparator<Nodes> {
		public int compare(Nodes s1, Nodes s2){
			return (s1.foralug + s1.depth) - (s2.foralug + s2.depth);
		}
}

class myComparator2 implements Comparator<Nodes> {
		public int compare(Nodes s1, Nodes s2){
			return s1.foralug - s2.foralug;
		}
}

class Algoritmos
{	
	static int myEscolha=0;
	static int totalgerados=0;
	static int totalusados=0;
	static int gd =0;
	static int alg =0;
	static LinkedList<Nodes> q = new LinkedList<>();
	static LinkedList<Nodes> auxiliar = new LinkedList<>();
	static LinkedList<Nodes> visited = new LinkedList<>();
	static Comparator<Nodes> comparator = new myComparator();
	static Comparator<Nodes> comparator2 = new myComparator2();
	static PriorityQueue<Nodes> pq = new PriorityQueue<>(comparator);
	static PriorityQueue<Nodes> pq2 = new PriorityQueue<>(comparator2);

	static String bfs(int[][] startconf, int[][] endconf){
		Nodes n = new Nodes(startconf,0,"",null);
		q.addFirst(n);
		visited.addFirst(n);
		while(q.peekFirst()!=null){
			Nodes aux = q.removeFirst();
			totalusados++;
			Boolean check = Nodes.isSolution(aux,endconf);
			if(check){
				System.out.println("Profundidade: " +aux.depth);
				System.out.println("Numero de nos usados: " +totalusados);
				System.out.println("Numero de nos gerados: " +totalgerados);
				return aux.path;
			}
			createchildNodes(aux, endconf);
			q.addAll(auxiliar);
			auxiliar.clear();
		}

		return "Solution not found";	
	
	}

	static String dfs (int[][] startconf, int[][] endconf){
		Nodes n = new Nodes(startconf,0,"",null);
		q.addFirst(n);
		visited.addFirst(n);
		while(q.peekFirst()!=null){
			Nodes aux = q.removeFirst();
			totalusados++;
			Boolean check = Nodes.isSolution(aux,endconf);
			if(check){
				System.out.println("Profundidade: " +aux.depth);
				System.out.println("Numero de nos usados: " +totalusados);
				System.out.println("Numero de nos gerados: " +totalgerados);
				return aux.path;
			}
			if(aux.depth < 20){
				createchildNodes(aux, endconf);
				q.addAll(0,auxiliar);
				auxiliar.clear();
			}

		}

		return "Solution not found";	
	}

	static String idfs (int[][] startconf, int[][] endconf, int maxdepth){
		Nodes n = new Nodes(startconf,0,"",null);
		q.addFirst(n);
		visited.addFirst(n);
		while(q.peekFirst()!=null){
			Nodes aux = q.removeFirst();
			totalusados++;
			Boolean check = Nodes.isSolution(aux,endconf);
			if(check){
				System.out.println("Profundidade: " +aux.depth);
				System.out.println("Numero de nos usados: " +totalusados);
				System.out.println("Numero de nos gerados: " +totalgerados);
				return aux.path;
			}
			if(aux.depth<maxdepth){
				createchildNodes(aux, endconf);
				q.addAll(0,auxiliar);
				auxiliar.clear();
			}
		}
		q.clear();
		visited.clear();
		return idfs(startconf, endconf, maxdepth+1);		
	}

	static String aStar(int[][] startconf, int[][] endconf, int escolha){
		alg=1;
		gd=1;
		myEscolha=escolha;
		Nodes n;
		if(escolha==1)
		 	n = new Nodes(startconf,0,"", Nodes.getFora(startconf,endconf),null);
		else
			n = new Nodes(startconf,0,"", Nodes.getSumMan(startconf,endconf),null);
		//System.out.println(n.sumdist+ " " +  n.foralug);
		pq.add(n);
		while(pq.peek()!=null){
			Nodes aux = pq.remove();
			totalusados++;
			if(Nodes.isSolution(aux,endconf)){
				System.out.println("Profundidade: " +aux.depth);
				System.out.println("Numero de nos usados: " +totalusados);
				System.out.println("Numero de nos gerados: " +totalgerados);				return aux.path;
			}
			createchildNodes(aux, endconf);
		}
		return "yes";
	}

	static String greedy(int[][] startconf, int[][] endconf, int escolha){
		alg=1;
		gd=2;
		myEscolha=escolha;
		Nodes n;
		if(escolha==1)
			n = new Nodes(startconf,0,"", Nodes.getFora(startconf,endconf),null);
		else 
			n = new Nodes(startconf,0,"", Nodes.getSumMan(startconf,endconf),null);
		pq2.add(n);
		while(pq2.peek()!=null){
			Nodes aux = pq2.remove();
			totalusados++;
			if(Nodes.isSolution(aux,endconf)){
				System.out.println("Profundidade: " +aux.depth);
				System.out.println("Numero de nos usados: " +totalusados);
				System.out.println("Numero de nos gerados: " +totalgerados);
				return aux.path;
			}
			createchildNodes(aux, endconf);
		}
		return "yes";

	}

	static void createchildNodes(Nodes n, int[][] endconf){
		int line = Nodes.getLine(n);
		int col = Nodes.getCol(n);
		int copied[][] = new int[4][4];

		//mover para cima
		if(line>0){
			copied = Nodes.copyTable(n);
			copied[line][col] = copied[line-1][col];
			copied[line-1][col] = 0;
			if(alg==0){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"U ",n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					auxiliar.addLast(u);
				}
			}
			else if(alg==1 && myEscolha==1){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"U ", Nodes.getFora(copied, endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
			else if(alg==1 && myEscolha==2){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"U ", Nodes.getSumMan(copied, endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
		}

		//mover para baixo
		if(line<3){
			copied = Nodes.copyTable(n);
			copied[line][col] = copied[line+1][col];
			copied[line+1][col] = 0;
			if(alg==0){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"D ",n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					auxiliar.addLast(u);
				}
			}
			else if(alg==1 && myEscolha==1){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"D ",Nodes.getFora(copied,endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
			else if(alg==1 && myEscolha==2){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"D ", Nodes.getSumMan(copied, endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
		}

		//mover para esquerda
		if(col>0){
			copied = Nodes.copyTable(n);
			copied[line][col] = copied[line][col-1];
			copied[line][col-1] = 0;
			if(alg==0){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"L ",n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					auxiliar.addLast(u);
				}
			}
			else if(alg==1 && myEscolha==1){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"L ",Nodes.getFora(copied,endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
			else if(alg==1 && myEscolha==2){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"L ", Nodes.getSumMan(copied, endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
		}

		//mover para a direita
		if(col<3){
			copied = Nodes.copyTable(n);
			copied[line][col] = copied[line][col+1];
			copied[line][col+1] = 0;
			if(alg==0){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"R ", n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					auxiliar.addLast(u);
				}
			}
			else if(alg==1 && myEscolha==1){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"R ", Nodes.getFora(copied,endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
			else if(alg==1 && myEscolha==2){
				Nodes u = new Nodes(copied, n.depth+1, n.path+"R ", Nodes.getSumMan(copied, endconf),n);
				if(!Nodes.repetidos(u)){
					totalgerados++;
					if(gd==1)
						pq.add(u);
					else if(gd==2)
						pq2.add(u);
				}
			}
		}

	}

}

