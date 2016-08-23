package com.yellon.treelistview.bean.TreeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuyanlong(email:276644135@qq.com)
 * @date 2016-08-22 19:04
 * @package com.yellon.treelistview.bean
 * @description Node  节点
 * @params
 */
public class Node {
    private int id;
    /**
     * 根节点pid = 0
     */
    private int pId = 0;

    private String name;

    /**
     * 当前的级别
     */
    private int level;

    /**
     * 是否展开
     */
    private boolean isExpand = false;

    private int icon ;
    /**
     * 下一级的子Node
     */
    private List<Node> children = new ArrayList<Node>();

    /**
     * 父Node
     */
    private Node father;

    public Node(){

    }

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    /**
     * 是否是根节点
     */
    public boolean isRoot(){
        return father == null;
    }

    /**
     * 判断父节点是否展开
     */
    public boolean isFatherExpand(){
        if (father == null)
            return false;
        return father.isExpand();
    }

    /**
     * 是否是叶子节点
     */
    public boolean isLeaf(){
        return children.size() == 0;
    }

    /**
     * 获取level
     */
    public int getLevel(){
        return father == null ? 0 :father.getLevel() + 1;
    }

    /**
     * 设置展开
     */
    public void setExpand(boolean isExpand){
        this.isExpand = isExpand;
        if(!isExpand){
            for (Node node : children){
                node.setExpand(isExpand);
            }
        }
    }

}