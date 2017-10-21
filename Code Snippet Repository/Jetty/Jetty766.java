    public Path getPath(Node from, Node to)
    {
        if (from == to)
        {
            return new Path();
        }

        // Perform a Breadth First Search (BFS) of the tree.
        Path path = breadthFirst(from,to,new CopyOnWriteArrayList<Path>(),new HashSet<Edge>());
        return path;
    }
