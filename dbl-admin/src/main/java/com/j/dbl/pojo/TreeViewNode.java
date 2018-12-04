package com.j.dbl.pojo;

import java.util.List;

public class TreeViewNode {

	private Integer id;
	private String text;
	private State state;
	private List<TreeViewNode> nodes;

	public TreeViewNode(Integer id, String text, State state, List<TreeViewNode> nodes) {
		this.id = id;
		this.text = text;
		this.state = state;
		this.nodes = nodes;
	}

	public TreeViewNode() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<TreeViewNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeViewNode> nodes) {
		this.nodes = nodes;
	}

	public void setState(Boolean checked){
		this.state = new State(checked);
	}
}

class State{
	private Boolean checked;

	public State(Boolean checked) {
		this.checked = checked;
	}

	public State() {
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}