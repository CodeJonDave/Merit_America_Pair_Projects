package com.techelevator;


import com.techelevator.search.SearchDomain;
import com.techelevator.search.SearchDomainException;
import com.techelevator.search.SearchEngine;
import com.techelevator.util.TELog;

import java.util.List;
import java.util.Scanner;

public class Application {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Step Two: Create TELog, and log the start of the application.
            TELog.log("Search application started"+"\n");

            // Step Four: Instantiate a Search Domain
            try {
                SearchDomain sd = new SearchDomain("data");
                TELog.log(sd.toString());

                // Step Six: Single word search
                //
                SearchEngine s1 = new SearchEngine(sd);
                s1.indexFiles();
                List<String> wordSearch = s1.search("squirrel");
                TELog.log(wordSearchToString(wordSearch));
                System.out.println(wordSearchToString(wordSearch));

                // Step Seven: Multiple word search
                //
                List<String> multiWordSearch = s1.search("telephone line");
                TELog.log(wordSearchToString(multiWordSearch));
                System.out.println(wordSearchToString(multiWordSearch));

            } catch (SearchDomainException e) {
                System.err.println("An error occurred: " + e.getMessage());
                TELog.log(e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TELog.closeLogger();
        }
    }
    private static String wordSearchToString(List<String> results){
        if(!results.isEmpty()){
            StringBuffer searchString = new StringBuffer();
            for (String result: results){
                searchString.append(result).append("\n");
            }
            return  searchString.toString();
        }
        else{
            return "The word could not be located";
        }
    }
}