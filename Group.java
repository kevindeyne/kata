package com.test.kata;

import java.util.Arrays;
import java.util.Stack;

public class Group {

	private static final String[] OPEN_SYMBOLS = new String[] { "(", "{", "[" };
	private static final String[] CLOSING_SYMBOLS = new String[] { ")", "}", "]" };

	public static boolean groupCheck(String s) {

		String[] split = s.split("");

		Stack<String> stack = new Stack<String>();
		int countClosedElements = 0;

		// go over every element from left to right
		for (String splitElement : split) {
			
			int openingSymbolIndex = getIndexOfSymbols(splitElement, OPEN_SYMBOLS);
			if (hasSymbol(openingSymbolIndex)) {
				//found an open symbol, add expected closing symbol to the stack
				stack.add(CLOSING_SYMBOLS[openingSymbolIndex]);
			} else {				
				int closingSymbolIndex = getIndexOfSymbols(splitElement, CLOSING_SYMBOLS);
				if (hasSymbol(closingSymbolIndex)) { //is a closed element
					//if no stack element matches up, we have a closing element too many - so count this
					countClosedElements++; 
					
					if (stack != null && !stack.isEmpty()) {
						if (!CLOSING_SYMBOLS[closingSymbolIndex].equals(stack.peek())) {
							//expected closing element does NOT match actual element
							return false;
						} else {
							//nicely closed off symbol, so pop() to remove from stack, and count down to remove from counter
							stack.pop();
							countClosedElements--;
						}
					}
				}
			}
		}

		//check if both the stack is empty (all opening elements closed up with the proper elements) and no overused closed elements exist
		return stack.isEmpty() && countClosedElements == 0;
	}

	public static boolean hasSymbol(int closingIndex) {
		return -1 != closingIndex;
	}

	public static int getIndexOfSymbols(String splitElement, String[] symbols) {
		return Arrays.asList(symbols).indexOf(splitElement);
	}

	public static void main(String args[]) {
		System.out.println(Group.groupCheck("()") == true); // true
		System.out.println(Group.groupCheck("({") == false); // false

		System.out.println(Group.groupCheck("()") == true); // true);

		System.out.println(Group.groupCheck("({})") == true); // true);
		System.out.println(Group.groupCheck("[[]()]") == true); // true);
		System.out.println(Group.groupCheck("[{()}]") == true); // true);

		System.out.println(Group.groupCheck("({") == false); // false);
		System.out.println(Group.groupCheck("{(})") == false); // false);
		System.out.println(Group.groupCheck("([]") == false); // false);
		System.out.println(Group.groupCheck("[])") == false); // false);
	}

}