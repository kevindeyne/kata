package com.test.kata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripleDouble {
	public static int tripleDouble(long num1, long num2) {	    
	    List<String> triples = determineTriples(num1);
	    return doTriplesContainDoubles(num2, triples);
	}
	  
	private static List<String> determineTriples(long num1) {
		Map<String, Integer> characterCount = getCharacterCountMap(num1);
				
		List<String> result = new ArrayList<String>();
		for(String character : characterCount.keySet()){
			if(characterCount.get(character) >= 3){
				result.add(character);
			}
		}
		
		return result;
	}

	public static Map<String, Integer> getCharacterCountMap(long num1) {
		Map<String, Integer> characterCount = new HashMap<String, Integer>();
		String[] numbersSplit = String.valueOf(num1).split("");
		
		for(String number : numbersSplit){
			int count = 0;
			if(characterCount.containsKey(number)){
				count = characterCount.get(number);
			}
			characterCount.put(number, ++count);			
		}
		return characterCount;
	}
	
	private static int doTriplesContainDoubles(long num2, List<String> triples) {
		Map<String, Integer> characterCount = getCharacterCountMap(num2);
		
		for(String character : characterCount.keySet()){
			if(characterCount.get(character) >= 2 && triples.contains(character)){
				return 1;
			}
		}
		
		return 0;
	}

	public static void main (String[] args){
		 System.out.println(TripleDouble.tripleDouble(451999277L, 41177722899L) == 1);
	}	  
}