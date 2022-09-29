import java.io.*;
import java.util.*;


/**
*@author Njabulo Mdluli
*
*<h1>AVLExperiment Application</h1>
*The AVLExperiment application interacts with the user through Terminal
*It takes in an integer in the main function and make randomizations according to that integer
*Makes use of the Vaccine class
*
*/




public class AVLExperiment {

    private ArrayList<Vaccine> va = new ArrayList<Vaccine> (); 
    private AVLTree<Vaccine> tree = new AVLTree<Vaccine> ();
    
    private static ArrayList<Integer> inserts = new ArrayList<Integer> ();
    private static ArrayList<Integer> finds = new ArrayList<Integer> ();

    /**
    This method opens a file and reads it
    *It then makes use of the VaccineArray object
    *and add the lines found in the file to the array in the VaccineArray class
    *Prints "File not found" error if the file is not found
    */
    
    public void readFile() {
    
        //Reads the contents of the file 
        
        File f = new File ("data/vaccinations.csv");
        
        try {
            Scanner sc = new Scanner (f);
        
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
           
                va.add(new Vaccine(line));

            }
        }
            
        catch (Exception e) {
            //Report an error
            System.out.println("File not found");
           } 
    }
    
    /**
    This method creates variations in the arraylist that contains the data
    *By swapping two random elements.
    *@param x is te number of randomisations to be made
    */
    
    public void makeVariations(int x) {
    	Random r = new Random();
    	int min = 0;
    	int max = 9919;
    	

    	
    	for (int i = 0; i < x; i++) {
    		int index_1 = r.nextInt(max - min) + min;
    		int index_2 = r.nextInt(max - min) + min;
    		Collections.swap(va, index_1, index_2);
    		}
    }
    	
    /**
    *This method writes the randomised data into a file
    *param x is the number of randomisations made in the file which makes it unique from the other files
    *prints the IOException message if there is an error
    */
    
    public void writeToFile(int x){
    	try {
    		FileWriter randoms = new FileWriter("data/Randomizations/"+x+"_randomisations.txt");
    		String line = "";
    		for (Vaccine a: va) {
    			line += a.toString()+"\n";
    		}
    		
    		randoms.write(line);
    		randoms.close();
    		
    	}
    	catch(IOException ioe) {
    		System.out.println("IOException: " + ioe.getMessage());
    	}
    }
    
    /**
    *This method adds the randomised data into the AVL Tree 
    *It subsequently adds the number of operations which are the instrumentations into the inserts array
    */
    
    public void addToTree() {
    	for (Vaccine v: va) {
    		tree.insert(v);
    		inserts.add(tree.insertOperations);
    	}
    }
    
    /**
    *This method searches for an entry in the AVL tree 
    *It then adds the number of operations done to find the entry into the finds array
    */
    
    public void find() {
    	for (Vaccine v:va){
    	BinaryTreeNode<Vaccine> a = tree.find(v);
    	finds.add(tree.findOperations);
    	}
    	
    }
    
    /**
    *This method writes the results to a file
    *The results are the number of operations it took to insert and find an element in the AVL Tree
    *@param x is the number of randoms done in the file
    */
    
    public void resultsToFile(int x) {
        try{
    		FileWriter results = new FileWriter("data/Results/"+x+"_RandomsResults.txt");
    		String lines = "Inserts  \t Finds   \n";
    		
    		for (int i = 0; i <  inserts.size(); i++){
    			lines += "  "+inserts.get(i)+"  \t\t   "+finds.get(i)+"\n";
    		}
    		results.write(lines);
    		results.close();
    		}
    	catch (IOException ioe) {
    		System.out.println("IOException: " + ioe.getMessage());
    		}
    
    }
    
    /**
    *This method gets the min, max and calculate the average in the arrays
    *And then append those results into a file for storing
    *@param x is the number of randomisations
    */
    public void graphsData(int x) {
    	Collections.sort(inserts);
    	Collections.sort(finds);
    	
    	int inserts_min = inserts.get(0);
    	int finds_min = finds.get(0); 
    	
    	
    	
    	
    	int inserts_total = 0;
    	int finds_total = 0;
    	for(int i = 0; i<inserts.size(); i++){
    		inserts_total = inserts_total+inserts.get(i);
    		finds_total = finds_total+= finds.get(i);
    		}
    		
	double inserts_avg = inserts_total / inserts.size();
	double finds_avg = finds_total / finds.size();
    	
    	int inserts_max = inserts.get(inserts.size() - 1);
    	int finds_max = finds.get(finds.size() - 1);
    	
    	
    	
    	 try {
         	String data = x +","+ inserts_min +","+ inserts_max +","+ inserts_avg +","+ finds_min +","+ finds_max +","+ finds_avg+"\n";
         	File f1 = new File("data/FinalResults_data.txt");
         	if(!f1.exists()) {
            		f1.createNewFile();
         	}

         	FileWriter fileWritter = new FileWriter(f1.getName(),true);
         	BufferedWriter bw = new BufferedWriter(fileWritter);
         	bw.write(data);
         	bw.close();
      } catch(IOException e){
		System.out.println("Something wrong occured");
      }
    
    }
    
    /**
    *This is the main method which is responsible for launching this program
    @param agrs is a list of Strings that can/not be keyed in
    */
    
    public static void main (String[] args) {
    
    	try{
    		AVLExperiment exp = new AVLExperiment();
    		exp.readFile();
    		exp.makeVariations(Integer.parseInt(args[0]));
    		exp.writeToFile(Integer.parseInt(args[0]));
    		exp.addToTree();
    		exp.find();
    		exp.resultsToFile(Integer.parseInt(args[0]));
    		exp.graphsData(Integer.parseInt(args[0]));
    	}
    	catch (Exception e) {
    		System.out.println("Something Not expected occured");
    	}
    }

}
