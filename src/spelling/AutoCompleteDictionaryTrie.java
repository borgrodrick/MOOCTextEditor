package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		TrieNode currNode = root;
		for (char c:word.toLowerCase().toCharArray())
		{
			TrieNode n = currNode.getChild(c);
			if (n == null){
				currNode = currNode.insert(c);
			}
			else {
				currNode = n;
			}
		}
		
		if (currNode.endsWord()){
			return false;
		}
		
		currNode.setEndsWord(true);
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
		return sizeChildren(root);
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		TrieNode currNode = root;
		for (char c:s.toLowerCase().toCharArray())
		{
			TrieNode n = currNode.getChild(c);
			if (n == null){
				return false;
			}
			else {
				currNode = n;
			}
		}
		
		return (currNode.endsWord()) ;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 List<String> completions = new ArrayList<String>();
    	 
    	 
    	 TrieNode curr = getNode(prefix);
    	 
    	 if (curr == null)
    		 return completions;
    	 
    	 queue.add(curr);
  		
    	 // && completions.size() > numCompletions
    	 while (!queue.isEmpty() && completions.size() != numCompletions){
    		 curr = queue.removeFirst();
    		 
    		 if (curr.endsWord()){
    			 completions.add(curr.getText());
    		 }
    		 
    		 TrieNode temp = curr;
    		 
    		 curr.getValidNextCharacters().forEach(c -> queue.add(temp.getChild(c)));
    		 
    		 
    	 }
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	private TrieNode getNode(String text){
 		TrieNode currNode = root;
		for (char c:text.toLowerCase().toCharArray())
		{
			TrieNode n = currNode.getChild(c);
			if (n == null){
				return null;
			}
			else {
				currNode = n;
			}
		}
		
		return currNode;
 		
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	private int sizeChildren(TrieNode curr){
 		int size = 0;
 		
 		if (curr == null) 
 			return 0;
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			if (next.endsWord())
 				size++;
 			
 			size = size + sizeChildren(next);
 		}
 		
 		return size;
 	}
 	

	
}