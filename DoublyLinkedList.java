/**
 *
 * @authors
 * Deeksha Lakshmeesh Mestha :  dxm172630
 * Vineet Vats : vxv180008
 */
package dxm172630;

import dxm172630.SinglyLinkedList;

import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class DoublyLinkedList<T> extends SinglyLinkedList<T> {
	//extending the SinglyLinkedList Entry class
	static class Entry<E> extends SinglyLinkedList.Entry<E> {

		Entry<E> prev;//node previous to the current node

		Entry(E x, Entry<E> nxt, Entry<E> prv) {

			super(x, nxt);
			this.prev = prv;

		}
	}

	public DoublyLinkedList() {
		head = new Entry<>(null, null, null);//head node with current value and pointers to previous and next nodes
		tail = head;
	}

	public Iterator<T> dllIterator() {
		return new dllIterator();
	}

	protected class dllIterator extends SinglyLinkedList<T>.SLLIterator {

		dllIterator() {
			cursor = head;
		}
		
		//method to check if a given node has previous node
		public boolean hasPrev() {
			return ((Entry<T>) cursor).prev != null;
		}

		//returns the element which is previous to the current node
		public T prev() {
			ready=true;
			cursor = ((Entry<T>) cursor).prev;
			prev = ((Entry<T>) cursor).prev;
			return ((Entry<T>) cursor).element;
		}

		//adds an element between the current node and the next node
		public void add(T x) {
			ready=false;
			Entry<T> currentNode = (Entry<T>) cursor;
			Entry<T> nextNode = (Entry<T>) cursor.next;
			Entry<T> newNode = new Entry<T>(x, nextNode, currentNode);
			currentNode.next = newNode;
			nextNode.prev = newNode;
			cursor = (Entry<T>)cursor.next;
			size++;

		}
		
		public void remove() {
			super.remove();
			((Entry<T>)(cursor.next)).prev=((Entry<T>)cursor).prev;//updating previous of the next node
			
		}
	}

	public void add(T x) {
		Entry<T> newNode = new Entry<T>(x, null, null);
		newNode.prev = (Entry<T>) tail;//updating previous of the newNode to tail
		add(newNode);
	}

	public static void main(String[] args) throws NoSuchElementException {
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		DoublyLinkedList<Integer> dlst = new DoublyLinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			dlst.add(Integer.valueOf(i));
		}
		dlst.printList();
		Iterator<Integer> dit = dlst.dllIterator();
		Scanner in = new Scanner(System.in);
		whileloop: while (in.hasNext()) {
			int com = in.nextInt();
			switch (com) {
			case 1: // Move to next element and print it
				if (dit.hasNext()) {
					System.out.println(dit.next());
				} else {
					break whileloop;
				}
				break;
			case 2: // Remove element
				dit.remove();
				dlst.printList();
				break;
			case 3://print the previous element
				if (((DoublyLinkedList<Integer>.dllIterator) dit).hasPrev()) {
					System.out.println(((DoublyLinkedList<Integer>.dllIterator) dit).prev());
				} else {
					break whileloop;
				}
				break;
			case 4: //add a new element after the current node
				System.out.println("Enter the number to be added: ");
				((DoublyLinkedList<Integer>.dllIterator) dit).add(in.nextInt());
					dlst.printList();
					break;
			default: // Exit loop
				break whileloop;
			}

		}
	}

}
