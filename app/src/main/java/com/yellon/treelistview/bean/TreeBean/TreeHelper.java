package com.yellon.treelistview.bean.TreeBean;

import com.yellon.treelistview.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuyanlong(email:276644135@qq.com)
 * @date 2016-08-23 09:41
 * @package com.yellon.treelistview.bean
 */
public class TreeHelper {
    /**
     * 传入数据bean，转换成排序后的Node
     *
     * @param datas
     * @param defaultExpandLevel
     * @return
     */

    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        //将用户转换为List<Node>
        List<Node> nodes = convetData2Node(datas);
        //拿到根节点
        List<Node> rootNodes = getRootNodes(nodes);
        //排序以及设置Node间关系
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    /**
     * 过滤出所有可见的node
     *
     * @param nodes
     */
    public static List<Node> filterVisibleNode(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            //如果是根节点，或者父节点是展开状态
            if (node.isRoot() || node.isFatherExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 将我们的数据转化为树的节点
     *
     * @param datas
     * @return
     */
    private static <T> List<Node> convetData2Node(List<T> datas)
            throws IllegalArgumentException, IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;

        for (T t : datas) {
            int id = -1;
            int pId = -1;
            String label = null;
            Class<? extends Object> clazz = t.getClass();
            Field[] declareFields = clazz.getDeclaredFields();
            for (Field f : declareFields) {
                if (f.getAnnotation(TreeNodeId.class) != null) {
                    f.setAccessible(true); //设置是否可以访问
                    id = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodePid.class) != null) {
                    f.setAccessible(true);
                    pId = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodeLabel.class) != null) {
                    f.setAccessible(true);
                    label = (String) f.get(t);
                }
                if (id != -1 && pId != -1 && label != null) {
                    break;
                }
            }
            node = new Node(id, pId, label);
            nodes.add(node);
        }

        /**
         * 设置Node间,父子关系；
         * 让每两个节点都比较一次，就可以设置其中的关系
         */
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getpId() == n.getId()) {
                    n.getChildren().add(m);
                    m.setFather(n);
                } else if (m.getId() == n.getpId()) {
                    m.getChildren().add(n);
                    n.setFather(m);
                }
            }
        }

        //设置图片
        for (Node n : nodes) {
            setNodeIcon(n);
        }
        return nodes;
    }

    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.isRoot()) {
                root.add(node);
            }
        }
        return root;
    }

    /**
     * 把一个节点上的所有内容都挂上去
     */
    private static void addNode(List<Node> nodes, Node node, int defaultExpandLevel, int currentLevel) {
        nodes.add(node);
        if (defaultExpandLevel >= currentLevel) {
            node.setExpand(true);
        }
        if (node.isLeaf()) {
            return;
        }
        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(nodes, node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);
        }
    }

    /**
     * 设置节点的图标
     */
    private static void setNodeIcon(Node node) {
        if (node.getChildren().size() > 0 && node.isExpand()) {
            node.setIcon(R.mipmap.tree_ex);
        } else if (node.getChildren().size() > 0 && !node.isExpand()) {
            node.setIcon(R.mipmap.tree_ec);
        } else {
            node.setIcon(-1);
        }

    }
} 