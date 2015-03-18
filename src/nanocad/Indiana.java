package nanocad;
import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.lang.String;

public class Indiana
{
   
   // private static textwin saveWin;
    public static String name,formula,a;
    public static char g,j,t,special;
    public static String search_str;
    public static LinkedList tosend = new LinkedList();
    public static int chk;
  //  private static newNanocad newnano;    
  
    public static void main(String []argv)
    {
	if( argv.length < 2 ) {
		System.err.println( "Insufficient arguments" );
		System.exit(-1);
	}
 
	System.err.println("In Java code");
	search_str = argv[0];
	
	// get the integer from the argument
	for( int i = 0; i < argv[1].length(); i++ )
		if( Character.isDigit(argv[1].charAt(i)) )
			chk = Character.digit(argv[1].charAt(i), 10 );


	String query_string="",query_st="";
	String atom1Name = null,atom1Num = null;
	String atom2Name = null,atom2Num = null;
	String atom3Name = null,atom3Num = null;

//         newnano.loadpdb("http://pine.ncsa.uiuc.edu/csd/temp/fileFromIndiana");         
//
  
	System.err.println("chk = " + chk + " search_str = " + search_str);

	if(chk == 0){  // => we passed a name
	    System.err.print("Hello world: ");
	    query_string ="http://www.iumsc.indiana.edu/db/search.jsp?start=1&compoundName=" + argv[0]+ "&raw=pdb";
	    System.err.println("chk = " + chk + " query_string= " + query_string);
	} else if(chk == 1) //=> we passed a Formula
	    {
		//Parse the formula string into alphabets and numbers
		// and then generate the query string reqd by Indiana
		int k=0;
		while(k<search_str.length()){
		    Character asj;
		    special =  search_str.charAt(0);
		    if(Character.isDigit(special)){
			System.exit(0); // is there a better aliter to exit here
		    }
		    g = search_str.charAt(k);
		    
		    if(Character.isUpperCase(g)){
			asj=new Character(g);
			tosend.add(asj.toString());
			k++;
			if(k>= search_str.length()){
			    tosend.add("1");
			    break ;
			}
			j = search_str.charAt(k);
			if(Character.isUpperCase(j)){
			    tosend.add("1");
			    asj = new Character(j);
			    tosend.add(asj.toString());
			    k++;
			    
			    if(k>= search_str.length()){
				tosend.add("1");
				break ;
			    }
			    else if(Character.isDigit(t = search_str.charAt(k))){
				asj = new Character(t);
				tosend.add(asj.toString());
				k++;
			    }
			    else if(Character.isLowerCase(t = search_str.charAt(k))){
				asj = new Character(t);
				String add = tosend.getLast().toString();
				String after = add + asj.toString();
				tosend.removeLast();
				tosend.addLast(after);
				k++;
				
				if(k>= search_str.length()){
				    tosend.add("1");
				    break ;
				}
				else if(Character.isDigit(t = search_str.charAt(k))){
				    asj = new Character(t);
				    tosend.add(asj.toString());
				    k++;
				}
				else
				    tosend.add("1");
			    }
			}
			else if(Character.isDigit(j)){
			    asj = new Character(j);
			    tosend.add(asj.toString());
			    // tosend.add("1");
			    k++;
			    if(k>= search_str.length()){
				break ;
			    }
			}
			else{
			    asj = new Character(j);
			    String test = tosend.getLast().toString();
			    String test1 = test + asj.toString();
			    tosend.removeLast();
			    tosend.addLast(test1);
			    k++;
			    
			    if(k>= search_str.length()){
				tosend.add("1");
				break ;
			    }
			    
			    else if(Character.isDigit(t = search_str.charAt(k))){
				asj = new Character(t);
				tosend.add(asj.toString());
				k++;
			    }
			    else
				tosend.add("1");
			    
			}
		    }  
		}
		
		atom1Name= tosend.get(0).toString();
		atom3Name= tosend.get(2).toString();
		atom1Num = tosend.get(1).toString();
		atom3Num = tosend.get(3).toString();
		
		
		
		int o=0,anothernumber=0;
		String initialquery = "", combined="";
		String must = "http://www.iumsc.indiana.edu/db/search.jsp?start=1";
		for(o=0;o<tosend.size();o++){
		    anothernumber++;
		    initialquery = "&" + "atom"+ anothernumber+"Name"+"="+ tosend.get(o).toString();
		    o++;
		    String something = "&atom" + anothernumber +"Relation==&atom"+ anothernumber+"Num=" + tosend.get(o).toString();
		    combined = combined + initialquery + something;
		}
		query_string = must + combined + "&raw=pdb";

		System.err.println( "chk = " + chk + " query_string = " + query_string );
	    }
	
	try
	    {
		System.err.println("Final value query_string is "+ query_string);
		URL url_indiana = new URL(query_string);
		URLConnection conn_indiana = url_indiana.openConnection();
		conn_indiana.connect();
		BufferedReader inFile = new BufferedReader (new InputStreamReader(conn_indiana.getInputStream()));
		
		String line = null;
		String final_file="";
		
		int counter = 0;
		while ((line = inFile.readLine()) !=null)
		    {
                        System.err.println("Value of line is "+line);
			if(counter <5){
			    counter++;
                          }
			else
			    final_file += line;
		    }
		PrintWriter fout
		   = new PrintWriter(new BufferedWriter(new FileWriter("/work/csd/temp/fileFromIndiana.pdb")));
		fout.print(final_file);
		fout.close();

	    }catch(Exception e3)
		{ 
		    System.err.println(e3);
		    e3.printStackTrace();
		    System.err.println("This is the last few lines of the program");
		}
    } 
}
