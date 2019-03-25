package com.rkb.transform;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@Component
@Scope("prototype")
public class NetSort {
	/**
	 * 包含所有node的list
	 */
	private List<Node> listall = new ArrayList<>();
	/**
	 * 盛放最终node顺序的list集合，这个顺序不是唯一的
	 */
	private List<Node> list = new ArrayList<>();
	/**
	 * 盛放最终node的current顺序的list集合，这个顺序不是唯一的
	 */
	private List<String> currents = new ArrayList<>();
	/**
	 * 暂时存放按顺序找到但是父节点还没有全部写入list的set集合，最后这个集合为null
	 */
	private Set<Node> set = new LinkedHashSet<>();
	
	

	public void  init_clear(){


		listall.clear();
		list.clear();
		currents.clear();
		set.clear();




	}



	/**
	 * 循环包含所有节点的list集合，得到输入节点，输入节点没有父节点，将输入节点存入最终list，将插入
	 * 最终list的元素从listall中移除
	 */
	public void GetFrist() {
		List<Node> del = new ArrayList<>();
		for (Node node : listall) {
			if (node.getLast().get(0).equals("")) {
				list.add(node);
				currents.add(node.getCurrent());
				del.add(node);
			}
		}
		for (Node node : del) {
			listall.remove(node);
		}
	}
	
	
	/**
	 * 找到next与last的对应关系，将对应上的node查找他的last是否都被包含于盛放最终结果的currents，
	 * 若被包含，则追加到最终list，若没有，则加入set，将插入最终list或者是set的元素从listall中移除
	 */
	public void NextToLast(Node node) {
		List<Node> del = new ArrayList<>();
		List<String> nexts = node.getNext();
		if ((nexts != null && nexts.size() > 0) && !nexts.get(0).equals("")) {
			for (String next : nexts) {
				for (Node n : listall) {
					if (next.equals(n.getCurrent()) ) {
						if (currents.containsAll(n.getLast())) {
							list.add(n);
							currents.add(n.getCurrent());
						}else {
							set.add(n);
						}
						del.add(n);
						
					}
				}
			}
			for (Node nn : del) {
				listall.remove(nn);
			}
		}
		
		
	}
	
	/**
	 * 每次找next与current之间的对应关系之前，现将set遍历一遍，若其中的node的last全部被包含于current，那么将
	 * 此node从set中移除，添加到最终list
	 */
	public void LoopSet() {
		List<Node> del = new ArrayList<>();
		for (Node n : set) {
			if (currents.containsAll(n.getLast())) {
				del.add(n);
				currents.add(n.getCurrent());
				list.add(n);
			}
		}
		for (Node nn : del) {
			set.remove(nn);
		}
	}
	
	
	/**
	 * 排序
	 */
	public void Sort() {
		
		int n = 0;
		int len = listall.size();
		GetFrist();
		while(list.size() < len ) {
			LoopSet();
			if (listall.size() >0 || list.size() < n) {
				NextToLast(list.get(n));
			}
//			System.out.println(list.get(n).getCurrent());
//
//			System.out.println(currents);
//			System.out.println(set);
			n++;
		}
		
	}
	
	
	
	
	
	

	public List<Node> getListall() {
		return listall;
	}


	public void setListall(List<Node> listall) {
		this.listall = listall;
	}


	public List<Node> getList() {
		return list;
	}


	public void setList(List<Node> list) {
		this.list = list;
	}


	public List<String> getCurrents() {
		return currents;
	}


	public void setCurrents(List<String> currents) {
		this.currents = currents;
	}


	public Set<Node> getSet() {
		return set;
	}


	public void setSet(Set<Node> set) {
		this.set = set;
	}


//	public static void main(String[] args) {
//
//
//		List<String> next = null;
//
//
//
//		Node node1 = new Node();
//		node1.setCurrent("aa");
//		next = new ArrayList<>();
//		next.add("bb");
//		next.add("ee");
//		next.add("ff");
//		node1.setNext(next);
//
//
//		Node node8 = new Node();
//		node8.setCurrent("hh");
//		next = new ArrayList<>();
//		next.add("cc");
//
//		node8.setNext(next);
//
//		List<String> list = null;
//
//
//
//		Node node2 = new Node();
//		node2.setCurrent("bb");
//		list = new ArrayList<>();
//		list.add("aa");
//		node2.setLast(list);
//		next = new ArrayList<>();
//		next.add("cc");
//
//		node2.setNext(next);
//
//
//		Node node3 = new Node();
//		node3.setCurrent("cc");
//		list = new ArrayList<>();
//		list.add("bb");
//		list.add("hh");
//		node3.setLast(list);
//		next = new ArrayList<>();
//		next.add("dd");
//		node3.setNext(next);
//
//
//		Node node4 = new Node();
//		node4.setCurrent("dd");
//		list = new ArrayList<>();
//		list.add("cc");
//		node4.setLast(list);
//
//
//		Node node5 = new Node();
//		node5.setCurrent("ee");
//		list = new ArrayList<>();
//		list.add("aa");
//		node5.setLast(list);
//
//
//		Node node6 = new Node();
//		node6.setCurrent("ff");
//		list = new ArrayList<>();
//		list.add("aa");
//		node6.setLast(list);
//		next = new ArrayList<>();
//		next.add("gg");
//		node6.setNext(next);
//
//		Node node7 = new Node();
//		node7.setCurrent("gg");
//		list = new ArrayList<>();
//		list.add("ff");
//		node7.setLast(list);
//
//
//		List<Node> nodes = new ArrayList<>();
//		nodes.add(node8);
//		nodes.add(node2);
//		nodes.add(node1);
//		nodes.add(node3);
//		nodes.add(node7);
//		nodes.add(node6);
//		nodes.add(node5);
//		nodes.add(node4);
//
//
//		for (Node node : nodes) {
//			System.out.println(node.getCurrent()+node.getLast());
//		}
//		NetSort netSort = new NetSort();
//		netSort.setListall(nodes);
//		netSort.Sort();
//		System.out.println(netSort.getCurrents());
//
//
//	}
	

}
