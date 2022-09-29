/**
*@Author Njabulo Mdluli
*<h1> Vaccine</h1>
*The Vaccine program make the vaccinations objects
*It makes use of Comparable class

*/

public class Vaccine implements Comparable <Vaccine> {

    private String country;
    private String date;
    private String vac_no;
    private String id;

    /**
    *This is a constructor accepting line of type String as a parameter
    *@param line contains a country name, date or/and number of vaccinations
    */

    public Vaccine (String line) {
        //Given a line like South Africa, 2022-01-01, 65463. It does the following
    
        String [] parts = line.split(",");
        this.country = parts[0];
        this.date = parts[1];
        this.id = country+","+date;
        
        if (parts.length == 3)
            vac_no = parts[2];
        else
            vac_no = "<not found>"; 
    }
    
    /**
    *This is a constructor accepting two parameters of type String
    *@param c is the name of the country
    *@param d is the date of the vaccinations
    */
    
    public Vaccine (String c, String d) {
    	this.country = c;
    	this.date = d;
    	vac_no = "<not found>"; 
    	this.id = c+","+d;
    }
    
    /**
    *This method accepts one parameter of type Vaccine
    *@param v is a vaccine object
    *Makes use of the compareTo method
    *@return integer
    */
    
    public int compareTo (Vaccine v) {
    	
 	return id.compareTo(v.id);   		
    	
    }
    
    
    /*
    *Accessor method
    *@return String which is the concantenation of the country and the number of vaccinations
    *That were made the given date
    */
    
    public String getResult() {
        String r = country+ " = " + vac_no;
        return r;
    }
    
    /**
    *@return the date
    */
    public String getDate() {
        return date;
    }
    
    /**
    *@return the country
    */
    public String getCountry() {
        return country;
    }
    /**
    *@return the id
    *Which is the concantenation of the country and the date
    */
    
    public String getId() {
        return id;
    }
    
    /**
    *@return country, date, number of vaccinations
    @overrides toString method
    */
    public String toString()  {
    	return country+", "+ date +", "+ vac_no;
    }
    
}
