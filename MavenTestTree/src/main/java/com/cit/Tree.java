/*
 * Name: Shane Bowen
 * ID: R00149085
 * Class: SD2-A 
 */

package com.cit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tree {
	
	Node root;

	public static ArrayList<Node> fourList = new ArrayList<Node>();
	public static ArrayList<Node> fiveList = new ArrayList<Node>();
	public static ArrayList<Node> sixList = new ArrayList<Node>();

	public void addNode(int key, String name, String prize) {
		
		// Create a new Node and initialize it
		Node newNode = new Node(key, name, prize);

		Tree.fourStarPrizes(newNode);
		Tree.fiveStarPrizes(newNode);
		Tree.sixStarPrizes(newNode);

		// If there is no root this becomes root
		if (root == null) {
			root = newNode;
		} else {
			// Set root as the Node we will start
			// with as we traverse the tree
			Node focusNode = root;

			// Future parent for our new Node
			Node parent;
			while (true) {
				// root is the top parent so we start
				// there
				parent = focusNode;
				// Check if the new node should go on
				// the left side of the parent node
				if (key < focusNode.key) {
					// Switch focus to the left child
					focusNode = focusNode.leftChild;
					// If the left child has no children
					if (focusNode == null) {
						// then place the new node on the left of it
						parent.leftChild = newNode;
						return; // All Done
					}
				} else { // If we get here put the node on the right
					focusNode = focusNode.rightChild;
					// If the right child has no children
					if (focusNode == null) {
						// then place the new node on the right of it
						parent.rightChild = newNode;
						return; // All Done
					}
				}
			}
		}
	}

	public static ArrayList<Node> fourStarPrizes(Node focusNode) {

		if (focusNode != null) {
			if(focusNode.key == 4)
			{
				// Traverse the left node
				fourStarPrizes(focusNode.leftChild);
				// Visit the currently focused on node
				fourList.add(focusNode);
				// Traverse the right node
				fourStarPrizes(focusNode.rightChild);
			}
		}

		return fourList;
	}

	public static ArrayList<Node> fiveStarPrizes(Node focusNode) {

		if (focusNode != null) {
			if(focusNode.key == 5)
			{
				// Traverse the left node
				fiveStarPrizes(focusNode.leftChild);
				// Visit the currently focused on node
				fiveList.add(focusNode);
				// Traverse the right node
				fiveStarPrizes(focusNode.rightChild);
			}
		}
		return fiveList;
	}

	public static ArrayList<Node> sixStarPrizes(Node focusNode) {

		if (focusNode != null) {
			if(focusNode.key == 6)
			{
				// Traverse the left node
				sixStarPrizes(focusNode.leftChild);
				// Visit the currently focused on node
				sixList.add(focusNode);
				// Traverse the right node
				sixStarPrizes(focusNode.rightChild);
			}
		}
		return sixList;
	}

	public String getName(int i, ArrayList<Node> n){

		return n.get(i).name;
	}

	public String getPrize(int i, ArrayList<Node> n){

		return n.get(i).prize;
	}
	
	public int getKey(int i, ArrayList<Node> n){

		return n.get(i).key;
	}
	
	File file = new File("Prize.txt");

	public void readFile(){

		try {

			Scanner kb = new Scanner(file);
			String line = null;
			while(kb.hasNext()){
				line = kb.nextLine();
				String[] value = line.split(":");

				if(value[0].equals("4")) {
					addNode(4, value[1], value[2]);
				}
				else if(value[0].equals("5")) {
					addNode(5, value[1], value[2]);
				}

				else if(value[0].equals("6")) {
					addNode(6, value[1], value[2]);
				}

			}

			kb.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	class Node {

		int key;
		String name;
		String prize;

		Node leftChild;
		Node rightChild;

		Node(int key, String name, String prize) {
			this.key = key;
			this.name = name;
			this.prize = prize;
		}
	}
}