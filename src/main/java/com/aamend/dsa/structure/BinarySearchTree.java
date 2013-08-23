package com.aamend.dsa.structure;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

	public int size;
	public Node root;

	static class Node {

		Node left;
		Node right;
		int value;

		public Node(int value) {
			this.value = value;
		}

	}

	public static class BSTException extends Exception {
		private static final long serialVersionUID = 1L;

		public BSTException() {
			super();
		}

		public BSTException(String message) {
			super(message);
		}

		public BSTException(String message, Throwable cause) {
			super(message, cause);
		}

		public BSTException(Throwable cause) {
			super(cause);
		}
	}

	// Constructor
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Delete a given value from the tree
	 * @param value
	 * @return true if deleted, false otherwise
	 * @throws com.aamend.dsa.structure.BinarySearchTree.BSTException if tree is empty
	 */
	public boolean delete(int value) throws BSTException {
		if (root == null) {
			throw new BSTException("BST tree is empty");
		}
		
		if (size == 1) {
			// Only 1 element here and that is the one to remove
			if (root.value == value) {
				root = null;
				size = 0;
				return true;
			} else {
				return false;
			}
		}

		return _delete(value, root);
	}

	private boolean _delete(int value, Node node) {

		// CASE 1: Node to remove is a leaf
		// CASE 2: Node to remove has a left subtree only
		// CASE 3: Node to remove has a right subtree only
		// CASE 4: Node to remove has both left and right subtree

		Node nodeToRemove = _findFirstNode(node, value);
		if (nodeToRemove == null) {
			// Element we want to remove does not exist
			return false;
		} else {
			// find parent
			Node parent = _findFirstNodeParent(root, value);
			if (nodeToRemove.left == null && nodeToRemove.right == null) {
				// CASE 1: leaf
				// is element we want to remove on left or right side of the
				// parent ?
				if (value < parent.value) {
					// was the left side, update it
					parent.left = null;
				} else {
					// was the right side
					parent.right = null;
				}
			} else if (nodeToRemove.left != null && nodeToRemove.right == null) {
				// CASE 2: left subtree
				// is element we want to remove on left or right side of the
				// parent ?
				if (value < parent.value) {
					// was the left side, update it
					parent.left = nodeToRemove.left;
				} else {
					// was the right side
					parent.right = nodeToRemove.left;
				}
			} else if (nodeToRemove.left == null && nodeToRemove.right != null) {
				// CASE 3: right subtree
				// is element we want to remove on left or right side of the
				// parent ?
				if (value < parent.value) {
					// was the left side, update it
					parent.left = nodeToRemove.right;
				} else {
					// was the right side
					parent.right = nodeToRemove.right;
				}
			} else {
				// CASE 4: both left and right
				// We promote maximum of left side element as replacement of the
				// node to delete
				// largest element is the leaf on the right side of the left subtree
				Node largestLeaf = nodeToRemove.left;
				while(largestLeaf.right != null){
					largestLeaf = largestLeaf.right;
				}
				
				// Remove the largest leaf from its parent ...
				Node parentLargest = _findFirstNodeParent(nodeToRemove, largestLeaf.value);
				parentLargest.right = null;
				
				// ... and attach it to the new parent
				nodeToRemove.value = largestLeaf.value;
			}
			size--;
			return true;
		}

	}

	/**
	 * Insert a new node in the tree If tree is empty, create root element
	 */
	public void insert(int value) {
		if (size == 0) {
			// Tree is empty, this value will be our new root node
			Node root = new Node(value);
			this.root = root;
			size = 1;
		} else {
			if(!contains(value)){
				_insert(value, root);
				size++;
			}
		}

	}

	private void _insert(int value, Node node) {
		if (value <= node.value) {
			// left
			if (node.left == null) {
				Node newNode = new Node(value);
				node.left = newNode;
			} else {
				_insert(value, node.left);
			}
		} else {
			// right
			if (node.right == null) {
				Node newNode = new Node(value);
				node.right = newNode;
			} else {
				_insert(value, node.right);
			}
		}
	}

	/**
	 * Find the minimum value of the tree
	 * 
	 * @return minimum value of the tree
	 * @throws com.aamend.dsa.structure.BinarySearchTree.BSTException
	 *             if tree is empty
	 */
	public int findMin() throws BSTException {
		if (root == null) {
			throw new BSTException("Cannot find minimum, tree is empty");
		} else {
			return _findMin(root);
		}
	}

	private int _findMin(Node node) {
		if (node.left == null) {
			return node.value;
		}
		return _findMin(node.left);
	}

	/**
	 * Find the maximum value of the tree
	 * 
	 * @return maximum value of the tree
	 * @throws com.aamend.dsa.structure.BinarySearchTree.BSTException
	 *             if tree is empty
	 */
	public int findMax() throws BSTException {
		if (root == null) {
			throw new BSTException("Cannot find maximum, tree is empty");
		} else {
			return _findMax(root);
		}
	}

	private int _findMax(Node node) {
		if (node.right == null) {
			return node.value;
		}
		return _findMax(node.right);
	}

	/**
	 * Check whether tree contains a given value
	 * 
	 * @param value
	 * @return true if found
	 */
	public boolean contains(int value) {
		if (root == null) {
			return false;
		} else {
			return _findFirstNode(root, value) == null ? false : true;
		}
	}

	private Node _findFirstNode(Node node, int value) {

		if (node == null) {
			return null;
		}

		if (node.value == value) {
			return node;
		} else {
			if (value < node.value) {
				// left side
				return _findFirstNode(node.left, value);
			} else {
				// right side
				return _findFirstNode(node.right, value);
			}
		}

	}

	private Node _findFirstNodeParent(Node node, int value) {

		if (node == null) {
			return null;
		}

		if (value <= node.value) {
			// left
			if (node.left == null) {
				// This child does not exist
				return null;
			}
			if (node.left.value == value) {
				return node;
			} else {
				return _findFirstNodeParent(node.left, value);
			}
		} else {
			// right
			if (node.right == null) {
				// This child does not exist
				return null;
			}
			if (node.right.value == value) {
				return node;
			} else {
				return _findFirstNodeParent(node.right, value);
			}
		}

	}

	/**
	 * Traverse tree in preorder left, then right, then parent
	 * 
	 * @return list of values
	 */
	public List<Integer> traversePreOrder() {
		List<Integer> list = new ArrayList<Integer>();
		_traversePreOrder(list, root);
		return list;
	}

	private void _traversePreOrder(List<Integer> list, Node node) {

		if (node != null) {
			_traversePreOrder(list, node.left);
			_traversePreOrder(list, node.right);
			list.add(node.value);
		}
	}

	/**
	 * Traverse tree in postorder parent, then left, then right
	 * 
	 * @return list of values
	 */
	public List<Integer> traversePostOrder() {
		List<Integer> list = new ArrayList<Integer>();
		_traversePostOrder(list, root);
		return list;
	}

	private void _traversePostOrder(List<Integer> list, Node node) {

		if (node != null) {
			list.add(node.value);
			_traversePostOrder(list, node.left);
			_traversePostOrder(list, node.right);
		}
	}

	/**
	 * Traverse tree in order left, then parent, then right
	 * 
	 * @return list of values
	 */
	public List<Integer> traverseInOrder() {
		List<Integer> list = new ArrayList<Integer>();
		_traverseInOrder(list, root);
		return list;
	}

	private void _traverseInOrder(List<Integer> list, Node node) {

		if (node != null) {
			_traverseInOrder(list, node.left);
			list.add(node.value);
			_traverseInOrder(list, node.right);
		}
	}

	public void print() {
		_print(root, 0);
	}

	public void _print(Node node, int level) {

		if (node == null)
			return;

		_print(node.right, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++) {
				System.out.print("|\t");
			}
			System.out.println("|-------" + node.value);
		} else {
			System.out.println(node.value);
		}
		_print(node.left, level + 1);
	}

	// Getters

	public int getSize() {
		return size;
	}

	public Node getRoot() {
		return root;
	}
}
