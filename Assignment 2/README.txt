Oluwatimi Owoturo - 8606957
Hello, TA

This is a note which will explain what works and what doesnt work in my implementation of myTrie and MyCompressedTrie

My Trie:

Correct ----
Inserting into MyTrie so insert(), Number of Nodes and Searching so search() works perfectly.

Known Bugs ---
printStringsInLexicoOrder() has been implemented and partially works. I have two elements printed which are wrong for some reason.






MyCompressedTrie-------
Correct ----------------------
MyCompressedTrie(MyTrie trie) -- Compressing the trie works perfectly. I have done this by converting each TreeNode in my Trie to a TreeNodeWithData everytime i encountered a node. Once I did, I had if statements
that determined if each node needed to be removed. If it did, it would remove it and replace it with its suitable child. If it did not, we would keep traversing

Number of nodes in compressed tree is correct!

Known bugs:
1. Test 2 doesnt work as this gives a stackoverflow error
2. printing in inorder does not work as this gives a type cast error. Note: This is not my written code
3. printStringsInLexicoOrder() was implemented but commented out as it gives a type cast error.


Thank you for reading,
Oluwatimi Owoturo
