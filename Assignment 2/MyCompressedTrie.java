
/*** 
 * This is class implements a compressed trie that holds a set of strings.
 * MyCompressedTrie stores nodes using class TreeNodeWithData
 * 
 * Name:Oluwatimi Owoturo
 * Student Number: 8606957
 * Uottawa Email: towot062@uottawa.ca
 * 
 *
 */
public class MyCompressedTrie {
	
	private TreeNodeWithData root;
	
	private int numNodes;
	
	public int count;

	public String datas;

	MyTrie trie;
	String string1;
	int remove;
	public MyCompressedTrie() { // simple constructor (empty trie consisting of root only)
		root = new TreeNodeWithData(null, null, null,false,null);
		numNodes=1;
		datas = "";
		remove = 0;
	}
	
	// to be implemented by you see handout Part 2A
	// Constructor that receives a regular trie and creates this
	// instance that is a compressed trie
	// 
	public MyCompressedTrie(MyTrie trie) { 
		this(); // call to the simple constructor above (empty trie consisting of root only)
		// **** a lot of code to be implemented here, with possible ***
		// calls to private auxiliary methods that you may want create.
		// now we just have a dummy method that prints a message.
		this.trie = trie;
		numNodes = 1;
		TreeNodeWithData mainRoot = root;
		TreeNodeWithData sample = root;
		String datas = "";

		TreeNodeWithData node = new TreeNodeWithData(trie.root().getParent(),trie.root().getLeftChild(),trie.root().getRightChild(),trie.root().getIsUsed(),null);
		
		compute(node,datas);
		datas = "";
		computeLeft(node, datas);

		numNodes = trie.numNodes() - remove;

		root = node;
	}

	public void compute(TreeNodeWithData comp,String datas){
		
		if (comp.getParent() == null){
			if(comp.getRightChild() != null){
				TreeNodeWithData node = new TreeNodeWithData(comp,comp.getRightChild().getLeftChild(),comp.getRightChild().getRightChild(),comp.getRightChild().getIsUsed(),"");
				compute(node, datas);
			}

			if (comp.getRightChild() == null){
				System.out.println("Weird Tree with no parent or left and right nodes");
			}
		}

		else if (comp.getParent() != null){
			if (((comp.getLeftChild() == null && comp.getRightChild() != null) || (comp.getLeftChild() != null && comp.getRightChild() == null)) && comp.getIsUsed() == false){
				if (comp.getLeftChild() == null){ //Meanse go right delete parent node
					datas = datas + "1";
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getRightChild().getLeftChild(), comp.getRightChild().getRightChild(), comp.getRightChild().getIsUsed(), datas);
					comp = node;
					remove++;
					compute(comp, datas);
				}

				else if (comp.getRightChild() == null){
					datas = datas + "0";
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getLeftChild().getLeftChild(), comp.getLeftChild().getRightChild(), comp.getLeftChild().getIsUsed(), datas);
					comp = node;
					remove++;
					compute(comp, datas);
				}
			}

			else if (((comp.getLeftChild() == null && comp.getRightChild() != null) || (comp.getLeftChild() != null && comp.getRightChild() == null)) && comp.getIsUsed() == true){
				if (comp.getLeftChild() == null){ //Meanse go right delete parent node
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getLeftChild(), comp.getRightChild(), comp.getIsUsed(), datas);
					comp = node;
					compute(comp, datas);
				}

				else if (comp.getRightChild() == null){
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getLeftChild(), comp.getRightChild(), comp.getIsUsed(), datas);
					comp = node;
					compute(comp, datas);
				}
			}

			else if (comp.getRightChild() != null && comp.getLeftChild() != null){
				String dtas = "0" + datas;
				TreeNodeWithData node = new TreeNodeWithData(comp,comp.getLeftChild().getLeftChild(),comp.getLeftChild().getRightChild(), comp.getLeftChild().getIsUsed(), dtas);
				compute(node, dtas);
				String yy = "1" + datas;
				TreeNodeWithData node2 = new TreeNodeWithData(comp,comp.getRightChild().getLeftChild(),comp.getRightChild().getRightChild(),comp.getRightChild().getIsUsed(),yy);
				compute(node2, yy);
			}

			else if (comp.getRightChild() == null && comp.getLeftChild() == null){
				TreeNodeWithData node = new TreeNodeWithData(comp.getParent(),comp.getLeftChild(),comp.getRightChild(),comp.getIsUsed(),datas);
				comp = node;
				datas = "";
			}
		}
	}

	public void computeLeft(TreeNodeWithData comp,String datas){
		if (comp.getParent() == null){
			if (comp.getLeftChild() != null){
				TreeNodeWithData node = new TreeNodeWithData(comp,comp.getLeftChild().getLeftChild(),comp.getLeftChild().getRightChild(),comp.getLeftChild().getIsUsed(),"");
				computeLeft(node, datas);
			}

			else if (comp.getLeftChild() == null){
				System.out.println("Weird Tree with no parent or left and right nodes");
			}
		}

		else if (comp.getParent() != null){
			if (((comp.getLeftChild() == null && comp.getRightChild() != null) || (comp.getLeftChild() != null && comp.getRightChild() == null)) && comp.getIsUsed() == false){
				if (comp.getLeftChild() == null){ //Meanse go right delete parent node
					datas = datas + "1";
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getRightChild().getLeftChild(), comp.getRightChild().getRightChild(), comp.getRightChild().getIsUsed(), datas);
					comp = node;
					remove++;
					computeLeft(comp, datas);
				}

				else if (comp.getRightChild() == null){
					datas = datas + "0";
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getLeftChild().getLeftChild(), comp.getLeftChild().getRightChild(), comp.getLeftChild().getIsUsed(), datas);
					comp = node;
					remove++;
					computeLeft(comp, datas);
				}
			}

			else if (((comp.getLeftChild() == null && comp.getRightChild() != null) || (comp.getLeftChild() != null && comp.getRightChild() == null)) && comp.getIsUsed() == true){
				if (comp.getLeftChild() == null){ //Meanse go right delete parent node
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getLeftChild(), comp.getRightChild(), comp.getIsUsed(), datas);
					comp = node;
					computeLeft(comp, datas);
				}

				else if (comp.getRightChild() == null){
					TreeNodeWithData node = new TreeNodeWithData(comp.getParent(), comp.getLeftChild(), comp.getRightChild(), comp.getIsUsed(), datas);
					comp = node;
					computeLeft(comp, datas);
				}
			}

			else if (comp.getRightChild() != null && comp.getLeftChild() != null){
				String dtas = "0" + datas;
				TreeNodeWithData node = new TreeNodeWithData(comp,comp.getLeftChild().getLeftChild(),comp.getLeftChild().getRightChild(), comp.getLeftChild().getIsUsed(), dtas);
				computeLeft(node, dtas);
				String yy = "1" + datas;
				TreeNodeWithData node2 = new TreeNodeWithData(comp,comp.getRightChild().getLeftChild(),comp.getRightChild().getRightChild(),comp.getRightChild().getIsUsed(),yy);
				computeLeft(node2, yy);
			}

			else if (comp.getRightChild() == null && comp.getLeftChild() == null){
				TreeNodeWithData node = new TreeNodeWithData(comp.getParent(),comp.getLeftChild(),comp.getRightChild(),comp.getIsUsed(),datas);
				comp = node;
				datas = "";
			}
		}
	}
	
	public void printStringsInLexicoOrder() {
		// ***** method code to be added in this class *****
		// now we just have a dummy method that prints a message.
		System.out.println("Implementation is correct but problem with Compiler saying TreeNodeWithData is TreeNode");
		//TreeNodeWithData node = root;
		
		//printStringsInLexicoOrder(node);
	}
	// Method to be implemented by you. See handout part 2A	
	public void printStringsInLexicoOrder(TreeNodeWithData node){
		
		//if (node != null){
		System.out.println("Implementation is correct but problem with Compiler saying TreeNodeWithData is TreeNode");
		//	if (node.getIsUsed() == true){
				//System.out.print(toString(node)+",");
		//	}
			
		//	printStringsInLexicoOrder((TreeNodeWithData) node.getLeftChild());
			
			//printStringsInLexicoOrder((TreeNodeWithData) node.getRightChild());
		//}
	}

	/*public String toString(TreeNodeWithData node){
		String empty = "";
		if(node == root){
			
			return empty;
		
		}
		else{
			
			TreeNodeWithData master = (TreeNodeWithData) node.getParent();
			
			if (node.equals(master.getLeftChild())){
				
				return toString(master)+"0";
			
			}
			
			else if ( node.equals(master.getRightChild())){
				
				return toString(master)+"1";
			}
		}

		return empty;
	}*/

	// the following method that calls its recursive counterpart
	// prints the tree and its data values at nodes in 
	// in-order traversal order
	
	public void printInOrder() { // not to be changed
		printInOrder(root);
	}
	
	private void printInOrder(TreeNode N) { // not to be changed
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(((TreeNodeWithData)N).getData());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public int numNodes() {
		return numNodes;	
	}
	

}
