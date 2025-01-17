
import java.io.BufferedReader;
import java.io.FileReader;

public class QueryOperations extends DoublyLinkedList{
	
	public void readFile(String file) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        String row;
	        while ((row = reader.readLine()) != null) {
	            String[] words = row.split(" ");
	            String country = words[0];
	            String populationStr = words[1];
	            long population = Long.parseLong(populationStr.replace(".", ""));
	            String capital_city = words[2];
	            String largest_city = words[3];
	            String language = words[4];
	            String currency = words[5];

	            insertLast(country, population, capital_city, largest_city, language, currency);
	        }
	        reader.close();
	    } catch (Throwable e) {
	        System.out.println("There's a problem with reading files. Please check the files. ");

	    }
	}

	//We define the operators in the txt file and the operations they need to be performed.
	public boolean checkCases(String value,String variables,String sign) {
		boolean is_true = false;
   
		if (sign.equals("=")) {
			is_true = (variables.toLowerCase().equals(value.toLowerCase()));
        }
        else if (sign.equals("<")) {
        	is_true = (variables.toLowerCase().compareTo(value.toLowerCase()) < 0);
        } else if (sign.equals(">")) {
        	is_true = (variables.toLowerCase().compareTo(value.toLowerCase()) > 0);
        }
	    return is_true;
	}
	
	
	public void addCountry(String row) {
		
	    String[] words = row.split(" ");
	    //words[0]="Add"
	    String country = words[1];
	    long population = Long.parseLong(words[2].replace(".", ""));
	    String capital_city = words[3];
	    String largest_city = words[4];
	    String language = words[5];
	    String currency = words[6];
	    
	    insertFirst(country, population, capital_city, largest_city, language, currency);
 }
	
	
	public void processQuery(String file) {
	    try {
	    	readFile(file); 
	        BufferedReader reader = new BufferedReader(new FileReader("query.txt"));
	        String row;
	        
	        while ((row = reader.readLine()) != null) {
	        	System.out.println(row);
	        	int i = 1;
	        	if (row.startsWith("Query print_all")) {
	        		print_all();
	        		
	        	}
	        	else if (row.startsWith("Query")) {
            	
	                String[] words = row.split(" ");
	                String variables = words[1]; // I defined population,country,language... as variables. 
	                String sign = words[2];
	                String value = words[3];
	                Node current = head;
	                
	                while (current != null) {
	                    boolean is_true = false;
	                    switch (variables) {
	                        case "population":
	                            long population = current.population;
	                            long long_value = Long.parseLong(value.replaceAll("\\.", ""));
	                            if (sign.equals(">")) {
	                            	is_true = (population > long_value);
	                            }  else if (sign.equals("=")) {
	                            	is_true = (population == long_value);
	                            } else if (sign.equals("<")) {
	                            	is_true = (population < long_value);
	                            }
	                            break; 
	                        case "country":
	                           String country = current.country;
	                           is_true = checkCases(value, country, sign); 
	                           break;
	                           
	                        case "currency":
	                        	String currency = current.currency;
	                        	is_true = checkCases(value, currency, sign);
	                            break;
	                            
	                        case "capital_city":

	                            String capital_city = current.capital_city;
	                            is_true = checkCases(value, capital_city, sign);
	                            break;
	                            
	                        case "largest_city":
	                            String largest_city = current.largest_city;
	                            is_true = checkCases(value, largest_city, sign);      
	                            break;
	                            
	                        case "official_language":
		                            String language = current.language;
		                            is_true = checkCases(value, language, sign);      
		                            break;    
	                           
	                    }
	                    
	                    if (is_true) {
	                     System.out.println("\t"+i +"." +current.country + " " + current.population  + " " +
	                     current.capital_city + " "  + current.largest_city +" " + current.language + " " + current.currency);
	                        i++;
	                    }
	                    current = current.next;
	                }
	                System.out.println();
	            } 
	        	else if (row.startsWith("Add")) {
	        		addCountry(row);
	                System.out.println();
	                
	            }
	            else if (row.startsWith("Delete")) {
	           String[] words = row.split(" ");
	           String countryName = words[1];
	           deleteNode(countryName);
	           System.out.println();
	           }
	        }
	        reader.close();
	    } catch (Throwable e) {
	        System.out.println("Something went wrong. Please make sure you wrote the queries correctly.");
	    }
	}

}
