package mini1;



public class DNASequence {
	

	  private String data;
	  

	  public DNASequence(String givenData)
	  {
	    data = givenData;
	  }
	  

	  public String toString()
	  {
	    return data;
	  }
	  

	  public boolean allValid()
	  {
	    for (int i=0; i < data.length(); i++) {
	    		char c = data.charAt(i);
	    			if (c!='A' && c!='T' && c!='C' && c!='G')
	    				return false;
	    }
	    return true;
	  }

	  public void fix()
	  {
	    String temp = "";
	    for (int i=0; i<data.length(); i++) {
	    		char c = data.charAt(i);
	    		if (c=='A' || c=='T' || c=='G' || c=='C')
	    			temp += c;
	    }
	    data = temp;
	  }
	  

	  public boolean isSubsequence(DNASequence other)
	  {
		  String temp1 = "";
		  String temp2 = "";
		  String temp3 = "";
		    for (int i=0; i<data.length(); i++) {
		    		char c = data.charAt(i);
		    		if (c=='A' || c=='T' || c=='G' || c=='C')
		    			temp1 += c;
		    }
		    for (int j=0; j<other.data.length(); j++) {
		    		char d = other.data.charAt(j);
		    		if (d=='A' || d=='T' || d=='G' || d=='C')
		    			temp2 += d;
		    }
		    int i=0;
		    int j=0;
		   
		    if (temp1.length()<temp2.length()) {
		    	return false;
		    }
		    
		    else { 
		    		while (i<temp2.length()) {
		    			if (j==temp1.length()) {
	    					break;
		    			}
		    				while (j<temp1.length()) {
		    					char c= temp2.charAt(i);
		    					char d=temp1.charAt(j);
		    					if (d==c) {
		    						temp3 +=d;
		    						i++;
		    					}
		    					if (temp2.equals(temp3)) {
		    						break;
		    					}
		    					j++;
		    					
		    				}	
		    				
		    		}	
		    if (temp2.equals(temp3))
    				return true;
			else 
				return false;
		    }
	  }
	  

	  public boolean willBond(char c1, char c2)
	  {
	    if (((c1=='A' && c2=='T') || (c1=='T' && c2=='A')) || ((c1=='C' && c2=='G') || (c1=='G' && c2=='C')))
	    		return true;
	    else
	    		return false;
	  }
	    

	  public void complement()
	  {
	    String temp = "";
	    for (int i=0; i<data.length(); ++i) {
	    		char c = data.charAt(i);
	    		if (c=='A') 
	    			temp += "T";
	    		else if (c=='T') 
	    			temp += "A";
	    		else if (c=='G') 
	    			temp += "C";
	    		else if (c=='C') 
	    			temp += "G";
	    		else
	    			temp += c;
	    }
	    	data=temp;
	    
	  }
	  

	  public int findMaxPossibleBonds(DNASequence other)
	  {
		  int maxbondspos=0;
		  int maxbondsneg=0;
		  int bonds;
		  for (int i=0; i<data.length(); i++) {
			  bonds=countBondsWithShift(other,i);
				  if (bonds>maxbondspos)
					  maxbondspos=bonds;
			  
		  }
		  for (int i=0; i>-other.data.length(); i--) {
			  bonds=countBondsWithShift(other,i);
			  if (bonds>maxbondsneg)
				  maxbondsneg=bonds;
		  }
		  return Math.max(maxbondspos, maxbondsneg);
	  }
	  

	  public int countBondsWithShift(DNASequence other, int shift)
	  {  
		 int bonds=0;

		 if (shift>=0) {
			 String temp1=data;
			 String temp2="";
			 for (int i=0; i<other.data.length()+shift-data.length(); i++) {
				 temp1+=" ";
			 }
			 for (int i=0; i<(shift); i++) {
				 temp2+=" ";
			 }
			 temp2+=other.data;
			 for (int i=0; i<(temp1.length()-temp2.length()); i++) {
				 temp2+=" ";
			 }
			 for (int i=0; i<temp2.length(); i++) {
				 char c = temp1.charAt(i);
				 char d = temp2.charAt(i);
				 if (willBond(c,d)) {
					 bonds++;
				 }
			 }
		 }
		 
		 if (shift<0) {
			 String temp1="";
			 String temp2=other.data;
			 for (int i=0; i<data.length()-shift-other.data.length(); i++) {
				 temp2+=" ";
			 }
			 for (int i=0; i<Math.abs(shift); i++) {
				 temp1+=" ";
			 }
			 temp1+=data;
			 for (int i=0; i<temp2.length()-temp1.length(); i++) {
				 temp1+=" ";
			 }
			 for (int i=0; i<temp1.length(); i++) {
				 char c = temp1.charAt(i);
				 char d = temp2.charAt(i);
				 if (willBond(c,d)) {
					 bonds++;
				 }
			 }
		 }
		  return bonds;
	  }
		  
	  
	  public String findBondsWithShift(DNASequence other, int shift)
	  {
		  String temp3="";
		  if (shift>=0) {
				 String temp1=data;
				 String temp2="";
				 for (int i=0; i<(shift); i++) {
					 temp2+=" ";
				 }
				 temp2+=other.data;
				 for (int i=0; i<(temp1.length()-(shift+other.data.length())); i++) {
					 temp2+=" ";
				 }
				 for (int i=0; i<temp1.length(); i++) {
					 char c = temp1.charAt(i);
					 char d = temp2.charAt(i);
					 if (willBond(c,d)) {
						 temp3+=c;
					 }
					 else {
						 temp3+="-"; 
					 }
				 }
			 }
		  if (shift<0) {
				 String temp1=data;
				 String temp2=other.data;
				 for (int i=0; i<data.length()-shift-other.data.length(); i++) {
					 temp2+=" ";
				 }
				 for (int i=0; i<temp1.length(); i++) {
					 char c = temp1.charAt(i);
					 char d = temp2.charAt(i+Math.abs(shift));
					 if (willBond(c,d)) {
						 temp3+=c;
					 }
					 else {
						 temp3+="-";
					 }
				 }
			 }
		  return temp3;
	  }
}