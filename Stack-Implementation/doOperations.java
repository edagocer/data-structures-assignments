import java.io.BufferedReader;
import java.io.FileReader;

public class doOperations {
	
	public void readFile(String file) {
		 try {
	            BufferedReader reader = new BufferedReader(new FileReader(file));
	            String row;
	            //we're reading every row in the file.
	            while ((row = reader.readLine()) != null) {
	            	StackOp(row);
	     	}
	            reader.close();
		    } catch (Throwable e) { //If an error is catched, print it.
		        System.out.println("ERROR  "+ e );
		}
	}
	
	public int precedence(char operator) {
		if(operator == '+' || operator ==  '-') 
			return 0;
		else if(operator == '*' || operator ==  '/') 
			return 1;
		return -1;
    }
	
	 public double doOperation(double num1, double num2, char operator) {
		 try {
		 	if(operator=='-') 
		 		return num1-num2;
		 	else if (operator == '+') 
		 		return num1+num2;
		 	else if (operator == '*') 
		 		return num1*num2;
		 	else if(operator == '/') {
		 		if(num2==0) 
                   throw new ArithmeticException("Number cannot divide by zero! ");
                else 
                    return num1/num2;
		 	}}catch (ArithmeticException e) {
		        System.out.print("ERROR " + e);
		    }
		 return Double.NaN; 
	}
	 
	 public void executeStackOp(MyStacks<Double> doubleStack,MyStacks<Character> opStack) {
		 double num1 = doubleStack.pop();
         double num2 = doubleStack.pop();
         double result = doOperation(num2, num1, opStack.pop());
         doubleStack.push(result); 
	 }
	
	 
   public void StackOp(String row) {
		 MyStacks<Double> doubleStack = new MyStacks<>();
		 MyStacks<Character> opStack = new MyStacks<>();
		 
		 for (int i = 0; i < row.length(); i++) {
		      char ch=row.charAt(i);
		      
		      if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
		    	//if the operator is at the beginning of the row
		        if(i==0) {
		             return; 
		        }
		    	//increase the k variable until it does not exceed the space between characters and the length of the line.    
		        int k = i + 1;
		        while (k < row.length() && Character.isWhitespace(row.charAt(k))) {
		                 k++;
		          }
		          //If the operator is at the end of the row
		        if (k == row.length() || Character.isWhitespace(row.charAt(k))) {
		              System.out.println("ERROR (Operator cannot be at the end of the row.) ");
		              return; 
		        }
		        if (opStack.isEmpty() || precedence(opStack.peek())<precedence(ch)) {
	                 opStack.push(ch);
	             }
		        
			    else{
	        	  try {
	        		  //do stack operations if the conditions are right
	        		 while(!opStack.isEmpty() && precedence(opStack.peek())>=precedence(ch)) {
	        			 executeStackOp(doubleStack,opStack);
	        		 }
	        		 opStack.push(ch); //Push the operator whether the operation is executed or not.
	             }catch(NullPointerException e) {
	 		    	System.out.println("ERROR  " + e);
	 		    	return;
	 		    }}}
			  
		      else if (Character.isDigit(ch)) {
		            StringBuilder numbers = new StringBuilder(); 
		            numbers.append(ch);
	        		//When reading a line, it takes each digit of a multi-digit number and adds it to numbers.
	        		while (i+1 < row.length() && Character.isDigit(row.charAt(i+1))) {
	        			numbers.append(row.charAt(i+1));
	                    i++;
	                }
		        	
		        	double doubleNum = Integer.parseInt(numbers.toString());
		        	numbers.append(doubleNum);//number is added to the stringBuilder
		        	doubleStack.push(doubleNum); //number is added to the stack.
		        }
		      
		      else if (Character.isWhitespace(ch)) { //If there's a space,continue the process
		              continue;
		      }
		 }
		//After the numbers and operators are pushed, if the stacks are not empty, they are emptied and the operations are performed.
		while (!opStack.isEmpty()) {
       	  executeStackOp(doubleStack,opStack);
	  }
			    
		double answer = doubleStack.peek();
	    if(answer == (int)answer) { //If the number is integer, do not display it as double
		    System.out.println((int) answer);
			} else
			    System.out.println(answer);
      	}
	  }
