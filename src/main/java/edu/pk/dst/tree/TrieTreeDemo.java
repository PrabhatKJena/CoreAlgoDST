import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class TrieTreeDemo {
	public static void main(String[] args) {
		/* Auto complete feature
			1. suggest //word
			2. apple // prefix based
			3. No special chars - lower case only
			
		1. add(String word)
		2. List<String>suggest(String word)
		
		Space
		N = number words of length L => O (N*L)
		
		Time
		Height of tree H
		search word of length L => O[L] + (H-L)*26
		*/
		
		TrieTree tree = new TrieTree();
		tree.update("blue");
		tree.update("black");
		tree.update("big");
		tree.update("small");
		tree.printTrie();
		
		System.out.println("b->" + tree.suggest("b"));
		System.out.println("bl->" + tree.suggest("bl"));
		System.out.println("sl->" + tree.suggest("sl"));

		/*
		  |--b
		    |--i
		      |--g $
		    |--l
		      |--a
		        |--c
		          |--k $
		      |--u
		        |--e $
		  |--s
		    |--m
		      |--a
		        |--l
		          |--l $
		
  		b->[big, black, blue]
		bl->[black, blue]
		sl->[]
		*/
	}
}


class TrieNode {
	int childrenSize = 26;
	boolean isLeaf;
	TrieNode[] children = new TrieNode[26];
	char ch;
	
	public TrieNode(char ch) {
		this.ch = ch;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(ch == '^' ? " " : ch);
		sb.append("[");
		for (int i = 0; i < childrenSize; i++) {
			if (children[i] != null) {
				sb.append(TrieTree.toChar(i)).append("|");
			}
		}
		sb.replace(sb.length() - 1, sb.length(), "]");
		sb.append(")");
		return sb.toString();
	}
}

class TrieTree {
	TrieNode root;
	private static final int indexOffSet = 97;
	
	public TrieTree() {
		this.root = new TrieNode('^');
	}
	
	public void printTrie() {
		printTrie(root, new StringBuilder(), 0);
	}
	
	private void printTrie(TrieNode node, StringBuilder prefix, int level) {
		if (node != null) {
			if (node.ch != '^') {
				System.out.println("  ".repeat(level) + "|--" + node.ch + (node.isLeaf ? " $" : ""));
			}
			final List<TrieNode> list = Arrays.stream(node.children).filter(Objects::nonNull).toList();
			for (TrieNode child : list) {
				prefix.append(node.ch);
				printTrie(child, prefix, level + 1);
				prefix.deleteCharAt(prefix.length() - 1); // Backtrack
			}
		}
	}
	
	public void update(String word) {
		final char[] charArray = word.toCharArray();
		TrieNode temp = root;
		for (final char c : charArray) {
			if (temp.children[c - indexOffSet] == null)
				temp.children[c - indexOffSet] = new TrieNode(c);
			temp = temp.children[c - indexOffSet];
		}
		temp.isLeaf = true;
	}
	
	public List<String> suggest(String word) {
		final ArrayList<String> result = new ArrayList<>();
		final char[] charArray = word.toCharArray();
		TrieNode current = root;
		for (final char c : charArray) {
			if (current.children[c - indexOffSet] == null) { // no prefix found
				return new ArrayList<>();
			}
			current = current.children[c - indexOffSet];
		}
		findAllWords(current, result, word);
		return result;
	}
	
	private void findAllWords(TrieNode current, ArrayList<String> result, String word) {
		if (current.isLeaf) {
			result.add(word);
		}
		int i = 0;
		while (i < current.children.length) {
			if (current.children[i] != null) {
				findAllWords(current.children[i], result, word + toChar(i));
			}
			i++;
		}
	}
	
	public static Character toChar(int index) {
		return (char) (index + indexOffSet);
	}
}
