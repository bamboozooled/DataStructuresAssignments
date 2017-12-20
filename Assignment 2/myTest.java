public class myTest{
    public static void main(String[] args){
        MyTrie tree = new MyTrie();

        tree.insert("111");
        tree.insert("01");
        tree.insert("0");
        tree.insert("0100");
        tree.insert("000");
        tree.insert("011");
        tree.insert("0101");
        System.out.println(tree.numNodes());
        System.out.println(tree.root().getParent());

        MyCompressedTrie tree2 = new MyCompressedTrie(tree);

        tree.printInOrder();

        System.out.println(tree2.numNodes());

        tree.printInOrder();
    }
}