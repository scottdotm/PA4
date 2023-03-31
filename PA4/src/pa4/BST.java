package pa4;

public class BST {

    protected BSTNode root;
    protected int size;

    public BST() {
        root = null;
        size = 0;
    }

    public BST(int A[]) {
        root = null;
        size = 0;
        for (int a : A) {
            insert(a);
        }
    }

    public BSTNode search(int key) {
        BSTNode tmp = root;
        while (tmp != null) {
            if (tmp.value == key) {
                return tmp;
            } else if (tmp.value > key) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        return null;
    }

    public BSTNode insert(int val) { // complete this method
        BSTNode newNode = new BSTNode(val);
    if (root == null) {
        root = newNode;
    } else {
        BSTNode current = root;
        BSTNode parent;
        while (true) {
            parent = current;
            if (val < current.value) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    newNode.parent = parent;
                    break;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    newNode.parent = parent;
                    break;
                }
            }
        }
    }
    size++;
    return newNode;
    }

    public boolean remove(int val) { // complete this method
        BSTNode toBeDeleted = search(val);
        if (toBeDeleted == null) {
            return false;
        }
        if (toBeDeleted.left != null && toBeDeleted.right != null) {
            BSTNode maxNode = findMax(toBeDeleted.left);
            toBeDeleted.value = maxNode.value;
            toBeDeleted = maxNode;
        }
        if (toBeDeleted.left == null && toBeDeleted.right == null) {
            removeLeaf(toBeDeleted);
        } else {
            removeNodeWithOneChild(toBeDeleted);
        }
        size--;
        return true;
    }

    private void removeLeaf(BSTNode leaf) { // complete this method
        BSTNode parent = leaf.parent;
    if (parent == null) { // leaf is root
        root = null;
    } else if (leaf == parent.left) { // leaf is left child
        parent.left = null;
    } else { // leaf is right child
        parent.right = null;
    }
    leaf.parent = null;
    }

    private void removeNodeWithOneChild(BSTNode node) { // complete this method
        BSTNode child = (node.left != null) ? node.left : node.right;
        BSTNode parent = node.parent;

        if (node == root) {
            root = child;
        } else {
            if (node == parent.left) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        if (child != null) {
            child.parent = parent;
        }
        // check if parent is null
        if (parent == null) {
            root = child;
        }
        node.parent = null;
    }

    public int getPredecessor(int key) { // complete this method
        BSTNode node = search(key);
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        if (node.left != null) {
            return findMax(node.left).value;
        } else {
            BSTNode parent = node.parent;
            while (parent != null && node == parent.left) {
                node = parent;
                parent = node.parent;
            }
            if (parent == null) {
                return Integer.MIN_VALUE;
            } else {
                return parent.value;
            }
        }
    }

    public int getSuccessor(int key) { // complete this method
        BSTNode node = search(key);
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        if (node.right != null) {
            return findMin(node.right).value;
        } else {
            BSTNode parent = node.parent;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = node.parent;
            }
            if (parent == null) {
                return Integer.MAX_VALUE;
            } else {
                return parent.value;
            }
        }
    }

    public int nearestNeighbour(int key) throws Exception { // complete this method
        int pred = getPredecessor(key);
        int succ = getSuccessor(key);

        if (pred == Integer.MIN_VALUE) {
            return succ;
        } else if (succ == Integer.MAX_VALUE) {
            return pred;
        } else {
            if (key - pred < succ - key) {
                return pred;
            } else {
                return succ;
            }
        }
    }

    private static BSTNode findMin(BSTNode node) {
        if (null == node) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static BSTNode findMax(BSTNode node) {
        if (null == node) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private static int getHeight(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    private void print(BSTNode node) {
        if (null != node) {
            System.out.print(node.toString() + " ");
            print(node.left);
            print(node.right);
        }
    }

    public int getHeight() {
        return getHeight(root);
    }

    public void print() {
        print(root);
    }

    public int getSize() {
        return size;
    }
}
