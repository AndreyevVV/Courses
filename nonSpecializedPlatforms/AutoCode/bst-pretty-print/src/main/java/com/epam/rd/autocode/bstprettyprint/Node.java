package com.epam.rd.autocode.bstprettyprint;

public class Node implements PrintableTree {
    int value;
    Node left, right;

    public void insertToTree(int v) {

        if (value == 0 || value == v) {
            value = v;
            return;
        }
        if (v > value) {
            if (left == null) {
                left = new Node();
            }
            left.insertToTree(v);
        } else {
            if (right == null) {
                right = new Node();
            }
            right.insertToTree(v);
        }
    }

    public void printTree(StringBuilder subTrees) {
        if (right != null) {
            right.printTree(subTrees, true, subPath(length(value)));
        }
        printNodeValue(subTrees);
        if (left != null) {
            left.printTree(subTrees, false, subPath(length(value)));
        }
    }

    private void printNodeValue(StringBuilder sb) {
        sb.append(value);

        if (left != null & right != null) {
            sb.append("┤");
        } else if (left != null & right == null) {
            sb.append("┐");
        } else if (right != null) {
            sb.append("┘");
        }
        sb.append('\n');
    }

    private void printTree(StringBuilder subTrees, boolean isRight, String offset) {
        if (right != null) {
            right.printTree(subTrees, true, offset + (isRight ? " " : "│") + subPath(length(value)));
        }
        subTrees.append(offset);
        if (isRight) {
            subTrees.append("┌");
        } else {
            subTrees.append("└");
        }
        printNodeValue(subTrees);
        if (left != null) {
            left.printTree(subTrees, false, offset + (isRight ? "│" : " ") + subPath(length(value)));
        }
    }

    @Override
    public void add(int i) {
        insertToTree(i);
    }

    @Override
    public String prettyPrint() {
        StringBuilder subTree = new StringBuilder();
        printTree(subTree);
        return subTree.toString();
    }

    Thread thread = new Thread()

    String subPath(int n) {
        return " ".repeat(Math.max(0, n));
    }

    int length(int value) {
        return Integer.toString(value).length();
    }
}
