/***
 * This is class implements a trie that holds a set of strings.
 * MyTrie stores stores nodes using class TreeNode
 * 
 * Name:Oluwatimi Owoturo
 * Student Number: 8606957
 * Uottawa Email: towot062@uottawa.ca
 * 
 *
 */

public class MyTrie {
	
	private TreeNode root = null;

	private boolean foundFalse;

	private int numNodes;

    // Constructor. Note that an empty trie (no strings added) contains the root node 
	public MyTrie() {
		root = new TreeNode(null, null, null,false); 
		foundFalse = false;
		numNodes=1;
	}

	// Method to be implemented by you. See handout part 1A
	public boolean insert(String s) {

		TreeNode node = root;
 
		// ***** method code to be added in this class *****
		// now we just have a dummy that prints  message and returns true.
		boolean completed = false;

		for (int i=0; i<s.length();i++){
			
			if (i == s.length()-1){
				completed = true;
			}
			
			node = insert(String.valueOf(s.charAt(i)), node,completed);

			if (foundFalse == true){
				foundFalse = false;
				return false;
			}


		}
		
		return true;

	}

	public TreeNode insert(String s, TreeNode node, boolean completed){
		
		TreeNode newNode2 = new TreeNode(node,null,null,completed);
		
		if (s.equals("1")){

			if (node.getRightChild() == null){
				TreeNode newNode = new TreeNode(node,null,null,completed);
				node.setRightChild(newNode);
				numNodes = numNodes + 1;
				newNode2 = newNode;
			}
			else if (node.getRightChild().getIsUsed() == false){
				TreeNode newNode = new TreeNode(node.getRightChild().getParent(),node.getRightChild().getLeftChild(),node.getRightChild().getRightChild(),completed);
				node.setRightChild(newNode);
				//numNodes = numNodes + 1;
				newNode2 = newNode;
			}
			else if (node.getRightChild().getIsUsed() == true && completed == false){
				newNode2 = node.getRightChild();

				//insert(s,node.getRightChild(),completed);
			}

			else if (completed == true && node.getRightChild().getIsUsed() == true){
				TreeNode newNode = new TreeNode(null,null,null,false);
				foundFalse = true;
				newNode2 = newNode;
			}
		}

		else if (s.equals("0")){
			if (node.getLeftChild() == null){
				TreeNode newNode = new TreeNode(node,null,null,completed);
				node.setLeftChild(newNode);
				//System.out.println("Here null");
				numNodes = numNodes + 1;
				newNode2 = newNode;
			}

			else if(node.getLeftChild().getIsUsed() == false){
				TreeNode newNode = new TreeNode(node.getLeftChild().getParent(),node.getLeftChild().getLeftChild(),node.getLeftChild().getRightChild(),completed);
				node.setLeftChild(newNode);
				//numNodes = numNodes + 1;
				//System.out.println("Here LC");
				newNode2 = newNode;
			}
			
			else if (node.getLeftChild().getIsUsed() == true && completed == false){
				newNode2 = node.getLeftChild();
				//System.out.println("Here BEFORE");
				//insert(s,node.getLeftChild(),completed);
			}

			else if (completed == true && node.getLeftChild().getIsUsed() == true){
				TreeNode newNode = new TreeNode(null,null,null,false);
				//System.out.println("Here");
				foundFalse = true;
				return newNode;
			}
		}

		return newNode2;
	}
	
	// Method to be implemented by you. See handout part 1A
	public boolean search(String s) {
		TreeNode node = root;

		boolean completed = false;

		for (int i = 0; i<s.length(); i++){

			if (i == s.length()-1){
				completed = true;
			}


			node = search(String.valueOf(s.charAt(i)), node, completed);

			if (foundFalse == true){
				foundFalse = false;
				return false;
			}
		}
		
		return true;	
	    
	}

	public TreeNode search(String s, TreeNode node, boolean completed){
		TreeNode newnode = new TreeNode(node,null,null,completed);
		if (s.equals("1")){
			if (node.getRightChild() == null){
				foundFalse = true;
			}

			else if (node.getRightChild().getIsUsed() == false && completed == false){
				newnode = node.getRightChild();
			}

			else if (node.getRightChild().getIsUsed() == false && completed == true){
				foundFalse = true;
			}

			else if (node.getRightChild().getIsUsed() == true && completed == true){
				foundFalse = false;
			}
		}

		if (s.equals("0")){
			if (node.getLeftChild() == null){
				foundFalse = true;
			}

			else if (node.getLeftChild().getIsUsed() == false && completed == false){
				newnode = node.getLeftChild();
			}

			else if (node.getLeftChild().getIsUsed() == false && completed == true){
				foundFalse = true;
			}

			else if (node.getLeftChild().getIsUsed() == true && completed == true){
				foundFalse = false;
			}
		}

		return newnode;
	}

	

	// Method to be implemented by you. See handout part 1A	
	public void printStringsInLexicoOrder() {
		// ***** method code to be added in this class *****
		// now we just have a dummy method that prints a message.

		TreeNode node = root;
		
		printStringsInLexicoOrder(node);
	}

	public void printStringsInLexicoOrder(TreeNode node){
		if (node != null){
			
			if (node.getIsUsed() == true){
				System.out.print(toString(node)+",");
			}
			
			printStringsInLexicoOrder(node.getLeftChild());
			
			printStringsInLexicoOrder(node.getRightChild());
		}
	}
	
	
	
	// the following method that calls its recursive counterpart
	// prints the tree and its boolean values at nodes in 
	// in-order traversal order
	
	public void printInOrder() { // not to be changed
		printInOrder(root);
	}
	private void printInOrder(TreeNode N) { // not to be changed
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(N.getIsUsed());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public TreeNode root() { // not to be changed
		return root;
	}
	
	public int numNodes() { // not to be changed
		return numNodes;
	}

	public String toString(TreeNode node){
		String empty = "";
		if(node == root){
			
			return empty;
		
		}
		else{
			
			TreeNode master = node.getParent();
			
			if (node.equals(master.getLeftChild())){
				
				return toString(master)+"0";
			
			}
			
			else if ( node.equals(master.getRightChild())){
				
				return toString(master)+"1";
			}
		}

		return empty;
	}

}
