package com.rkb.transform;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")
public class SimpleSort {
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
	 * 循环包含所有节点的list集合，得到输入节点，输入节点没有父节点，将输入节点存入最终list，将插入
	 * 最终list的元素从listall中移除
	 */
	public void GetFrist() {
		List<Node> del = new ArrayList<>();
		for (Node node : listall) {
			if (node.getLast()==null) {
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
	 * 排序
	 */
	
	public void  Sort() {
		GetFrist();

		List<Node> listnum = null;


            while (listall.size() > 0 ) {
                listnum = new ArrayList<>();
                for (Node node : listall) {
                    if (currents.containsAll(node.getLast())) {
                        listnum.add(node);
                    }
                }
                for (Node i : listnum) {
                    list.add(i);
                    currents.add(i.getCurrent());
                    listall.remove(i);
                }
            }
        }
		



	public void setListall(List<Node> listall) {
		this.listall = listall;
	}

	public List<String> getCurrents() {
		return currents;
	}

	public List<Node> getListall() {
		return listall;
	}

	public List<Node> getList() {
		return list;
	}

	public void setList(List<Node> list) {
		this.list = list;
	}

	public void setCurrents(List<String> currents) {
		this.currents = currents;
	}
}
