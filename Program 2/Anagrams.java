/**************************************************************/
/* Jeffrey Ni
/* Login ID: 015253589
/* CS 3310, Spring 2023 
/* Programming Assignment 2
/* Anagrams class: finds all sets of anagrams in given word.txt file
/**************************************************************/

// import packages needed
import java.io.*;
import java.util.*;

// Anagrams class definition
public class Anagrams 
{
    
    public static void main(String[] args) throws IOException 
    {
        // prompts user to enter file path
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        scanner.close();
        
        // create map to store sets of anagrams 
        Map<String, List<String>> anagrams = new HashMap<>();
        
        // reads all the words from the file into a list and disregarding the case
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String word = line.trim().toLowerCase();
                String signature = getSignature(word);
                List<String> listOfAnagrams = anagrams.computeIfAbsent(signature, key -> new ArrayList<>());
                listOfAnagrams.add(word);
            }
        }
        
        // only adds the sets with anagrams more than 1 which is any besides the original
        List<List<String>> setOfAnagramss = new ArrayList<>();
        for (List<String> listOfAnagrams : anagrams.values()) 
        {
            if (listOfAnagrams.size() > 1) 
            {
                setOfAnagramss.add(listOfAnagrams);
            }
        }
        
        Collections.sort(setOfAnagramss, Comparator.comparing(list -> list.get(0)));
        
        // outputs into a file called anagrams.txt
        File output = new File("anagrams.txt");
        try (PrintWriter writer = new PrintWriter(output)) 
        {
            for (List<String> sets : setOfAnagramss) 
            {
                writer.println(sets);
            }
        }
    }
    
    // helper method to get signature of word
    private static String getSignature(String word) 
    {
        char[] characters = word.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }
    
}
