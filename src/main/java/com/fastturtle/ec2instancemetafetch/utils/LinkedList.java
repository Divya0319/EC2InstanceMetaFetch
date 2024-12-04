package com.fastturtle.ec2instancemetafetch.utils;


public class LinkedList<T> implements LinkedListService {
    public LinkedList() {
        length = 0;
    }
    
    ListNode head;
    ListNode tail;
    private int length = 0;
    
    public synchronized ListNode getHead() {
        return head;
    }
    
    @Override
    public synchronized void insert(Integer data) {
    	ListNode newNode = new ListNode(data);
    	
    	// checking if list is empty
    	
    	if(head == null) {
    		head = newNode;
    		tail = newNode;
    	} else {
    		tail.setNext(newNode);
    		tail = newNode;
        
    	}
    	length++;
    }
    
    @Override
    public synchronized void insert(Integer data, int position) {
        ListNode temp = head;
        int pos = 1;
        while(pos != position-1) {
            temp = temp.getNext();
            pos++;
        }
        
    	ListNode newNode = new ListNode(data);
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        length++;
    }
    
    public synchronized void insertAndCreateLoop(Integer data, int position) {
        ListNode temp = head;
        int pos = 1;
        while(pos != position-1) {
            temp = temp.getNext();
            pos++;
        }
        
    	ListNode newNode = new ListNode(data);
        temp.setNext(newNode);
        newNode.setNext(temp);
        length++;
        
        System.out.println("Loop created from " + temp.getData() + " to " + newNode.getData());
    }
    
    @Override
    public synchronized ListNode deleteAtBegin() {
    	ListNode nodeIntegeroDelete = null;
        if(head == null) {
            System.out.println("List is empty");
        }
        else {
            
        nodeIntegeroDelete = head;
        head = head.getNext();
        length--;
        
        }
        
        return nodeIntegeroDelete;
    }
    
    @Override
    public synchronized ListNode deleteAtEnd() {
    	ListNode nodeIntegeroDelete = null;
        if(head == null) {
            System.out.println("List is empty");
        }
        else {
            
        ListNode temp = head;
        while(temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }
        nodeIntegeroDelete = temp.getNext();
        temp.setNext(null);
        length--;
        
        }
        
        return nodeIntegeroDelete;
    }
    
    @Override
    public synchronized ListNode delete(int position) {
    	
    	ListNode nodeIntegeroDelete = null;
        if(head == null) {
            System.out.println("List is empty");
        } 
        else {
            ListNode temp = head;
        
        if(position < 0) 
            position = 1;
        if(position > getLength()) 
            position = getLength();
        
        if(position == 0) {
        	nodeIntegeroDelete = head;
            head = head.getNext();
        } 
        else {
            for(int i = 2; i <= position-1; i++) {
                temp = temp.getNext();
            }
            
            nodeIntegeroDelete = temp;
            temp.setNext(temp.getNext().getNext());            
            
        }
        length--;
        
        }  
        
        return nodeIntegeroDelete;
    }
    
    @Override
    public int getLength() {
        return length;
    }
    
    public int getPosition(Integer data) {
        ListNode temp = head;
        int pos = 1;
        while(temp != null) {
            if(temp.getData() == data) {
                return pos;
            }
            
            pos++;
            temp = temp.getNext();
        }
        
        return -1;
    }
    
    @Override
    public boolean isEmpty() {
    	return head == null;
    }
    
    
    public String toString() {
        String result = "[";
        if(head == null) {
            return result + "]";
        }
        result = result + head.getData();
        ListNode temp = head.getNext();
        while(temp != null) {
           result = result + ", " + temp.getData();
           temp = temp.getNext();
        }
        
        return result + "]";
    }
    
    public static String toString(ListNode head) {
        StringBuilder result = new StringBuilder("[");
        
        if(head != null) {
        	result.append(head.getData());
        	ListNode temp = head.getNext();
        	while(temp != null) {
        		result.append(", ").append(temp.getData());
        		temp = temp.getNext();
        	}
        }
        
        result.append("]");
        
        return result.toString().replaceAll("\\s+", "");
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.insert(1);
        System.out.println(ll.toString());
        ll.insert(2);
        System.out.println(ll.toString());
        ll.insert(3);
        System.out.println(ll.toString());
        ll.insert(4);
        System.out.println(ll.toString());
        ll.insert(5);
        System.out.println(ll.toString());
        ll.insert(6);
        System.out.println(ll.toString());

    }
}



