package com.j.dbl.pojo;

import java.util.List;

public class TreeNode {

    private Integer id;
    private String text;
    private String data;
    private String remark;
    private List<TreeNode> nodes;

    public TreeNode(Integer id, String text, String data, String remark, List<TreeNode> nodes) {
        this.id = id;
        this.text = text;
        this.data = data;
        this.remark = remark;
        this.nodes = nodes;
    }

    public TreeNode() {
    }

    public long getId() {
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

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
