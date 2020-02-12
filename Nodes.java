class Nodes{
	
	int tab[][] = new int[4][4];
	int depth;
	String path;
	Nodes pai;
	int foralug;


	Nodes(int t[][], int d, String p, Nodes pa){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				tab[i][j] = t[i][j];
			}
		}
		
		depth = d;
		path = p;
		pai = pa;
		foralug =0;

	}

	Nodes(int t[][], int d, String p, int f, Nodes pa){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				tab[i][j] = t[i][j];
			}
		}
		depth = d;
		path = p;
		pai = pa;
		foralug = f;
	}

	static boolean isSolution(Nodes n, int[][] configf){
		int i, j;
		for(i=0;i<4;i++){
			for(j=0;j<4;j++){
				if(n.tab[i][j]!=configf[i][j])
					return false;
			}
		}
		return true;
	}

	static int getLine(Nodes n){
		int linei=0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(n.tab[i][j]==0){
					linei= i;
					return linei;
				}
			}
		}
		return linei ;
	}

	static int getCol(Nodes n){
		int coli=0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(n.tab[i][j]==0){
					coli = j;
					return coli;
				}
			}
		}
		return coli;
	}

	static int[][] copyTable(Nodes n){
		int copy[][] = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				copy[i][j]= n.tab[i][j];
			}
		}
		return copy;
	}

	static void printConfig(Nodes n){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.print(n.tab[i][j]);
			}
		}
		System.out.println();
	}

	static int getFora(int[][] config, int[][] configf){
		int sum=0;
		for(int i=0; i<4;i++){
			for(int j=0;j<4;j++){
				if(config[i][j]!=configf[i][j])
					sum++;
			}
		}
		return sum;
	}

	static int getSumMan(int[][] config, int[][] configf){
		int configArray[] = convert(config);
		int configfArray[] = convert(configf);
		int sum=0;
		int pos =0;
		for(int i=0; i<16;i++){
			if(configArray[i]!=configfArray[i] && configArray[i]!=0){
				pos = configArray[i];
				for(int j=0;j<16;j++){
					if(configfArray[j]==pos)
						break;
					sum++;
				}
			}
		}
		return sum;

	}

	static boolean repetidos(Nodes n){
		Nodes aux = n.pai;
		while(aux != null){
			if(isSolution(n,aux.tab))
				return true;
			aux=aux.pai;
		}
		return false;
	}

	static int[] convert(int[][] config){
		int[] converter = new int[16];
		int t =0;
		for(int i=0; i<4;i++){
			for(int j=0;j<4;j++){
				converter[t]= config[i][j];
				t++;
			}
		}
		return converter;
	}
}