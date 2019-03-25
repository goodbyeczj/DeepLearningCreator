package com.rkb.transform;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Scope("prototype")
public class Node {
	
	private List<String> last;
	private String current;
	private List<String> next;
	
	
	
	public Node(List<String> last, String current, List<String> next) {
		super();
		this.last = last;
		this.current = current;
		this.next = next;
	}
	
	
	
	public Node() {
		super();
	}



	public List<String> getLast() {
		return last;
	}
	public void setLast(List<String> last) {
		this.last = last;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public List<String> getNext() {
		return next;
	}
	public void setNext(List<String> next) {
		this.next = next;
	}
	
	
	
	
	
	

}
