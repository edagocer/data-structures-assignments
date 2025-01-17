
class DoublyLinkedList {
	class Node{
		 String country, capital_city, largest_city, language, currency;
		 long population;
		 Node prev,next;
		 
		 Node(String country, long population, String capital_city, String largest_city, String language, String currency){
			    this.country = country;
		        this.population = population;
		        this.capital_city = capital_city;
		        this.largest_city = largest_city;
		        this.language = language;
		        this.currency = currency;
		        this.prev = null;
		        this.next = null;
		 }
	}
	 
	 Node head,tail = null;
	 
	 //Adds the countries in input.txt to the list in order
	 public void insertLast(String country, long population, String capital_city, String largest_city, String language, String currency) {
		    Node newNode = new Node(country, population, capital_city, largest_city, language, currency);
		    if (head == null) {
		        head = newNode;
		        tail = newNode;
		    } 
		    else {
		        tail.next = newNode;
		        newNode.prev = tail;
		        tail = newNode;
		    }
	}
	 
	 //If a new country is added, it adds it at the beginning of the list
	 public void insertFirst(String country, long population, String capital_city, String largest_city, String language, String currency) {
		    Node newNode = new Node(country, population, capital_city, largest_city, language, currency);
		    Node current = head;
	        while (current != null) {
	            if (current.country.toLowerCase().equals(newNode.country.toLowerCase())) {
			    	System.out.println("\tThis country already exists in the list.");
	                return;
	            }
	            current = current.next;
	        }
		   
		    if (head == null) {
		        head = newNode;
		        tail = newNode;
		    } 
		    else {
		        newNode.next = head;
		        head.prev = newNode;
		        head = newNode;
		    }
		    System.out.println("\tA new country has been added.");

		    
		}
	 

	 public void deleteNode(String countryName) {
		    Node current = head;
		    if (current.country.toLowerCase().equals(countryName.toLowerCase())) {
		        head = head.next;
		        head.prev = null;
		        return;
		    }
		    while (current != null) {
		        if (current.country.toLowerCase().equals(countryName.toLowerCase())) {
		        	//if we try to delete last node
		            if (current.next == null) {
		                current.prev.next = null;
		                tail = current.prev;
		            } 
		            else { 
		                current.prev.next = current.next;
		                current.next.prev = current.prev;
		            }
		           
		        	System.out.println("\t" +"Country removed from the list. ");
		        	return;
		   }
		        current = current.next;
		 }
		       System.out.println("\t" + "Country " + countryName + " is not in the list.");

		}
	 
	 public void print_all() {
	        Node current = head;
	        int i = 1;
	        
	        while (current != null) {
	           System.out.println("\t" +i+ "."+current.country + " " + current.population  + 
	          " " +current.capital_city + " "  + current.largest_city +" " + current.language + " " + current.currency);
	        	i++;
	            current = current.next;
	        }
	        System.out.println();
	    }

}


