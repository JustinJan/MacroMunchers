/*
    CS179G - Part 1 
    
    Group 9
    Alyza Malunao 861065506
    Brittany Seto 861058099
    Justin Jan    861070174
*/

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.concurrent.*; 

/**
 * Example program to list links from a URL.
 * [user@server]./crawler.sh <seed_File:seed.txt> <num_pages: 10000> <hops_away: 6>  <output_dir>
 * args[0] = seed_file.txt 
 * args[1] = num_pages
 * args[2] = hops away
 * args[3] = output_dir.txt
 */

public class Web_crawler {
 
    public static void main(String[] args) {

    	String seedFile = args[0];
    	int num_pages = Integer.parseInt(args[1]); 
    	int hops = Integer.parseInt(args[2]); 
    	String outFile = args[3];  

    	String line; 
        boolean shouldFollow; 
        int init_depth = 0; 
        int curr_pages = 0; 
        BlockingQueue<Crawl> main_queue = new LinkedBlockingQueue<Crawl>();
		HashSet<String> set_of_URLs = new HashSet<String>(); ; 

        RobotExclusionUtil robot = new RobotExclusionUtil(); 
        Thread_share temp_share = new Thread_share(main_queue, set_of_URLs); 
        List<Crawler_thread> thread_list = new ArrayList<Crawler_thread>();

        File dir = new File(outFile); 
        dir.mkdir(); 

        try{
            FileReader inputFile = new FileReader(seedFile);  
            BufferedReader bufferReader = new BufferedReader(inputFile); 

            //read from seeds.txt
            while((line = bufferReader.readLine()) != null) {
            	
            	//check Robots.txt file to see if it can crawl 
            	shouldFollow = robot.robotsShouldFollow(line); 

            	//add crawls into queue
            	if(shouldFollow){
            		Crawl c = new Crawl(init_depth, line, outFile, temp_share, hops); 
            		temp_share.add_into_queue(c); 
            		temp_share.add_into_set(c.url);
            	}
            }
            bufferReader.close();
        }catch(Exception e) {
          	//System.out.println("Error while reading file line by line: " + e.getMessage());
        }
    	
    	while(curr_pages < num_pages){
            if(temp_share.getit() < 20){
        		if(!temp_share.isEmpty()){
	    	 		Crawler_thread thread = new Crawler_thread(temp_share.pop());
                    thread_list.add(thread); 
	    	 		thread.start();
		    		++curr_pages;
                    temp_share.inc();
                    System.out.println(curr_pages); 
    	    	}
            }
	    }
        try{
            Thread.sleep(20000);    
        }catch(Exception e){}
        
        System.exit(0); 
    	
    	return; 
    }
 
}





