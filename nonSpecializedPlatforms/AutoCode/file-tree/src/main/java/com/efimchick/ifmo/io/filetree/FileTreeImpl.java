package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import static com.efimchick.ifmo.io.filetree.TreeNode.getFileSize;

public class FileTreeImpl implements FileTree {

    @Override
    public Optional<String> tree(Path path) {
        if (path == null) return Optional.empty();
        if (!path.toFile().exists()) return Optional.empty();

        File file = path.toFile();
        String result = "";
        if (!file.isDirectory()){
            result = file.getName() + " " + getFileSize(file.toPath()) + " bytes";
            return Optional.of(result);
        }
        TreeNode<File> DirTree = TreeNode.createDirTree(file);
        result =  TreeNode.renderDirectoryTree(DirTree);

        return Optional.of(result);
    }

}
