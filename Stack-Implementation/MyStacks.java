
class Node<E> {
	public E data;
	public Node<E> next;
	
	public Node(E data) {
		this.data = data;
	}
}

class MyStacks<E> {
	public Node<E> top;

    public MyStacks() {
        top = null;
    }
    public boolean isEmpty() {
		return top==null; //is top is null return true.
	}

    public void push(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }
	
	public E pop() {
        if (isEmpty()) {
            return null;
        }
        E data = top.data;
        top = top.next;
        return data;
    }
	
	public E peek() {
        if (isEmpty()) 
            return null;
        else 
        	return top.data;
    }
}
