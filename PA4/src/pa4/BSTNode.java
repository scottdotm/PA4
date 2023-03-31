package pa4;
public class BSTNode {

	int value;
	BSTNode left, right, parent;

	BSTNode(int val) {
		// a node is always inserted as a leaf
		value = val;
		left = null;
		right = null;
		parent = null;
	}

	public String toString() {
		if (parent == null)
			return "<" + value + ", null>";
		else {
			return "<" + value + ", " + parent.value + ">";
		}
	}
        
}
