package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class TreeNode<T> implements Iterable<TreeNode<T>> {

    public T data;
    public TreeNode<T> parent;
    public List<TreeNode<T>> children;

    private final List<TreeNode<T>> elementsIndex;

    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<>();
        this.elementsIndex = new LinkedList<>();
        this.elementsIndex.add(this);
    }

    public void addChild(T child) {
        TreeNode<T> childNode = new TreeNode<>(child);
        childNode.parent = this;
        this.children.add(childNode);
        this.registerChildForSearch(childNode);
    }

    private void registerChildForSearch(TreeNode<T> node) {
        elementsIndex.add(node);
        if (parent != null)
            parent.registerChildForSearch(node);
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "[data null]";
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return new TreeNodeIter<>(this);
    }

    public static TreeNode<File> createDirTree(File folder) {

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        TreeNode<File> DirRoot = new TreeNode<>(folder);

        File[] listFilesSorted = folder.listFiles();
        assert listFilesSorted != null;
        listFilesSorted = Arrays.stream(listFilesSorted).sorted((o1, o2) -> {
            if (o1.isDirectory() & !o2.isDirectory()) return -1;
            if (!o1.isDirectory() & o2.isDirectory()) return 1;
            return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        }).toArray(File[]::new);
        for (File file : listFilesSorted) {
            if (file.isDirectory()) {
                appendDirTree(file, DirRoot);
            } else {
                appendFile(file, DirRoot);
            }
        }
        return DirRoot;
    }



    public static void appendDirTree(File folder, TreeNode<File> DirRoot) {
        DirRoot.addChild(folder);
        File[] listFilesSorted = folder.listFiles();
        assert listFilesSorted != null;
        listFilesSorted = Arrays.stream(listFilesSorted).sorted((o1, o2) -> {
            if (o1.isDirectory() & !o2.isDirectory()) return -1;
            if (!o1.isDirectory() & o2.isDirectory()) return 1;
            return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        }).toArray(File[]::new);
        for (File file : listFilesSorted) {
            if (file.isDirectory()) {
                appendDirTree(file,
                        DirRoot.children.get(DirRoot.children.size() - 1));
            } else {
                appendFile(file,
                        DirRoot.children.get(DirRoot.children.size() - 1));
            }
        }
    }

    public static void appendFile(File file, TreeNode<File> filenode) {
        filenode.addChild(file);
    }


    public static String renderDirectoryTree(TreeNode<File> tree) {
        List<StringBuilder> lines = renderDirectoryTreeLines(tree);
        String newline = "\n";
        StringBuilder sb = new StringBuilder(lines.size() * 20);
        for (StringBuilder line : lines) {
            sb.append(line);
            sb.append(newline);
        }
        return sb.toString();
    }

    public static List<StringBuilder>
    renderDirectoryTreeLines(TreeNode<File>
                                     tree) {
        List<StringBuilder> result = new LinkedList<>();
        result.add(new StringBuilder().append(tree.data.getName()).append(" ").append(getFileSize(tree.data.toPath())).append(" bytes"));
        Iterator<TreeNode<File>> iterator = tree.children.iterator();
        while (iterator.hasNext()) {
            List<StringBuilder> subtree =
                    renderDirectoryTreeLines(iterator.next());
            if (iterator.hasNext()) {
                addSubtree(result, subtree);
            } else {
                addLastSubtree(result, subtree);
            }
        }
        return result;
    }

    private static void addSubtree(List<StringBuilder> result,
                                   List<StringBuilder> subtree) {
        Iterator<StringBuilder> iterator = subtree.iterator();
        //subtree generated by renderDirectoryTreeLines has at least one
        //line which is tree.getData()
        result.add(iterator.next().insert(0, "├─ "));
        while (iterator.hasNext()) {
            result.add(iterator.next().insert(0, "│  "));
        }
    }

    private static void addLastSubtree(List<StringBuilder> result,
                                       List<StringBuilder> subtree) {
        Iterator<StringBuilder> iterator = subtree.iterator();
        //subtree generated by renderDirectoryTreeLines has at least
//        one
//        line which is tree.getData()
        result.add(iterator.next().insert(0, "└─ "));
        while (iterator.hasNext()) {
            result.add(iterator.next().insert(0, "   "));
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Sergey\\IdeaProjects\\file-tree\\src\\test\\resources\\test1");
        File file = path.toFile();

        // File file = new File("./");
        TreeNode<File> DirTree = createDirTree(file);
        String result = renderDirectoryTree(DirTree);
        System.out.println(result);
        try {
            File newTextFile = new File("./DirectoryTree.txt");

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(result);
            fw.close();

        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }

    public static long getFileSize(Path path) {
        long size = 0;
        try (Stream<Path> walk = Files.walk(path)) {

            size = walk
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.out.printf("Failed to get size of %s%n%s", p, e);
                            return 0L;
                        }
                    })
                    .sum();

        } catch (IOException e) {
            System.out.printf("IO errors %s", e);
        }
        return size;
    }


}