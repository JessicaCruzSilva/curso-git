
public class TesteVariaveis {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 for(int linha = 0; linha < 5; linha++) {
	            for (int coluna = 0; coluna < 5; coluna++) {
	                if (coluna>linha ) {
	                    break;
	                }
	                System.out.print( coluna);
	            }
	            System.out.println();
	        }
		
	}

}
