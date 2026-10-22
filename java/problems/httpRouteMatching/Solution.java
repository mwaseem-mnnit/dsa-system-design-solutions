package problems.httpRouteMatching;

import java.util.ArrayList;
import java.util.List;

/**
 * /api/v1/users/{userId}/orders
 * /api/v1/users/{userId}/orders/{orderId}
 * /api/v1/users/{userId}/details -> /api/v1/users/<anything other than events></>/details
 * /api/v1/users/events/details -> /api/v1/users/events/details
 *
 **/
public class Solution {
    private TreeNode root;

    Solution() {
        this.root = new TreeNode(false, "");
    }

    /**
     * registerHandler
     * split the route on "/", paths
     * parent = this.root
     * iterate over the paths and create node if doesn exists
     *
     *      normalisedPath = path.remove({,})
     *      isParam = true/false
     *      if true
     *          if parent.paramPath == null,
     *              create paramPath
     *          parent = paramPath
     *      else
     *          find normalisedPath in parent.childPath
     *          if notFound
     *              create new childPath and add to parent.childPath list
     *          parent = currentChildPath
     *
     * set the route for parent
     * return
     *
     * getHandler
     * split the pattern on "/", paths
     * currentNode = this.root
     * iterate over the paths and create node if doesnt exists
     *      first match with parent.childPaths
     *      then if paramPath exists select
     *      else return not found
     * return the route of current node
     * **/
    void registerHandler(String route) {
        if (route == null || route.isEmpty()) {
            return;
        }
        // validate route
        String[] paths = route.split("/");
        TreeNode parent = this.root;
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            boolean isParam = path.contains("}") || path.contains("{");
            String normalisedPath = !isParam ? path : path.replace("{", "").replace("}", "");
            if(isParam) {
                if(parent.paramPath == null) {
                    parent.paramPath = new TreeNode(true, normalisedPath);
                }
                parent = parent.paramPath;
            }
            else {
                TreeNode currentPath = null;
                for (TreeNode childPath : parent.childPaths) {
                    if(childPath.pathKey.equals( normalisedPath)) {
                        currentPath = childPath;
                        break;
                    }
                }
                if(currentPath == null) {
                    currentPath = new TreeNode(false, normalisedPath);
                    parent.childPaths.add(currentPath);
                }
                parent = currentPath;
            }
        }
        parent.route = route;
    }

    String getHandler(String pattern) {
        String[] paths = pattern.split("/");
        TreeNode currentNode = this.root;
        for (String path : paths) {
            boolean isFound = false;
            for (TreeNode childPath : currentNode.childPaths) {
                if(childPath.pathKey.equals(path)) {
                    currentNode = childPath;
                    isFound = true;
                    break;
                }
            }
            if(!isFound && currentNode.paramPath != null) {
                currentNode = currentNode.paramPath;
            } else if(!isFound) {
                return "Not Foud";
            }
        }
        return currentNode.route;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.registerHandler("/api/v1/user/{userId}/orders");
        solution.registerHandler("/api/v1/user/{userId}/payments");
        solution.registerHandler("/api/v1/user/{userId}/details");
        solution.registerHandler("/api/v1/user/events/details");
        System.out.println(solution.getHandler("/api/v1/user/events/details"));
        System.out.println(solution.getHandler("/api/v1/user/event/details//"));
        System.out.println(solution.getHandler("/api/v1/user/123/"));
        System.out.println(solution.getHandler("/api/v1/user/123/orders"));
    }
}

class TreeNode {
    boolean isParameter;
    String pathKey;
    List<TreeNode> childPaths;
    TreeNode paramPath;
    String route;

    TreeNode(boolean isParameter, String pathKey) {
        this.childPaths = new ArrayList<>();
        this.isParameter = isParameter;
        this.pathKey = pathKey;
    }
}