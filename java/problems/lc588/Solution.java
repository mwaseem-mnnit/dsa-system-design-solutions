package problems.lc588;

import java.util.*;

class FileSystem {
    Node root;

    public FileSystem() {
        root = new Node("/");
    }

    public List<String> ls(String path) {
        Node node = this.findNode(path);
        if(node.childrenList.isEmpty()) {
            if(node.data.isEmpty()) {
                return List.of();
            }
            return List.of(node.name);
        }

        List<String> result = new ArrayList<>(node.childrenList.stream().map(it -> it.name).toList());
        result.sort(Comparator.naturalOrder());
        return result;
    }

    public void mkdir(String path) {
        String[] pathList = Arrays.stream(path.split("/"))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);
        this.createPath(root, pathList, 0);
    }

    public void addContentToFile(String filePath, String content) {
        this.mkdir(filePath);
        Node node = this.findNode(filePath);
        node.data.append(content);
    }

    public String readContentFromFile(String filePath) {
        Node node = this.findNode(filePath);
        return node.data.toString();
    }

    private Node findNode(String path) {
        String[] pathList = Arrays.stream(path.split("/"))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);
        Node node = root;
        int index = 0;
        while (index < pathList.length) {
            for (Node child : node.childrenList) {
                if (child.name.equals(pathList[index])) {
                    node = child;
                    index++;
                    break;
                }
            }
        }
        return node;
    }

    private void createPath(Node node, String[] pathList, int index) {
        if (index == pathList.length) {
            return;
        }

        for (Node child : node.childrenList) {
            if (Objects.equals(child.name, pathList[index])) {
                createPath(child, pathList, index + 1);
                return;
            }
        }

        Node child = new Node(pathList[index]);
        node.childrenList.add(child);
        createPath(child, pathList, index + 1);
    }

    public static void main(String[] args) {
        FileSystem obj = new FileSystem();
//        obj.mkdir("/a/b/c");
//        obj.mkdir("/a/b/c");
//        obj.addContentToFile("/", "hello");
//        System.out.println(obj.ls("/"));
//        System.out.println(obj.readContentFromFile("/"));
//        obj.addContentToFile("/a/b/c", "hello");
//        System.out.println(obj.readContentFromFile("/"));
//        System.out.println(obj.readContentFromFile("/a/b/c"));
//        obj.addContentToFile("/a/b/c/d", "hello");
//        System.out.println(obj.readContentFromFile("/a/b/c/d"));
        /// ["FileSystem","ls","mkdir","mkdir","mkdir","mkdir","ls","addContentToFile","readContentFromFile","addContentToFile"]
        ///  [[],["/"],["/gh"],["/e"],["/jfo"],["/gh/znflyvnd"],["/gh"],["/mhdmck","v"],["/mhdmck"],["/bbigs","kzdi"]]

        System.out.println(obj.ls("/"));
        obj.mkdir("/gh");
        obj.mkdir("/e");
        obj.mkdir("/jfo");
        obj.mkdir("/gh/znflyvnd");
        System.out.println(obj.ls("/gh"));
        obj.addContentToFile("/mhdmck", "v");
        System.out.println(obj.readContentFromFile("/mhdmck"));
        obj.addContentToFile("/bbigs", "kzdi");
    }
}

class Node {
    String name;
    List<Node> childrenList;
    StringBuilder data;

    Node(String name) {
        this.name = name;
        this.childrenList = new ArrayList<>();
        this.data = new StringBuilder();
    }
}
/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */