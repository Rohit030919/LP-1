import java.util.*;

public class Pass{
	public static void main(String args[]){
		String[][] symbolTable = {
		{"","START","101",""},
		{"","MOVER","BREG",""},
		{"AGAIN","MULT","BREG","TERM"},
		{"","MOVER","CREG","TERM"},
		{"EMPTY","ADD","CREG","N"},
		{"","MOVEN","CREG","TERM"},
		{"","LTROG","",""},
		{"","DS","2",""},
		{"ONE","DC","1",""},
		{"TERM","DC","1",""},
		{"","END","",""},
		};

		int i,j;
		for(i=0; i<symbolTable.length; i++){
			for(j=0; j<symbolTable[i].length; j++){
				System.out.print("\t"+symbolTable[i][j]);
			}
			System.out.println();
		}
		
		int symCount=0;
		System.out.println("Symbols:");
		for(i=0; i<symbolTable.length; i++){
			String sym = symbolTable[i][0];
			if(!sym.isEmpty()){
				symCount++;
				System.out.println(sym);
			}
		}
		System.out.println("Total Number of Symbols is:"+symCount);

		String firstLiteral = null;
		boolean newPool = true;
		int poolCount = 0;
		for(i=0; i<symbolTable.length; i++){
			String mnemonic = symbolTable[i][1];
			String op = symbolTable[i][2];
			if(op.matches("\\d+")){
				if(newPool && firstLiteral == null){
				firstLiteral = op;
				}
			newPool=false;
			}
			if(mnemonic.equals("LTROG") || mnemonic.equals("END")){
				if(firstLiteral!=null){
					poolCount++;
					System.out.println("Pool:"+poolCount+":Starts With:"+firstLiteral);
					firstLiteral = null;
					newPool = true;
				}
			}
		}
		System.out.println("Total Number Of Pool is:"+poolCount);

	}
}