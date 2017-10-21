    private Path breadthFirst(Node from, Node destination, CopyOnWriteArrayList<Path> paths, Set<Edge> seen)
    {
        // Add next unseen segments to paths.
        boolean edgesAdded = false;
        if (paths.size()==0)
            paths.add(new Path());

        for (Path path : paths)
        {
            Set<Edge> next = findEdgesFrom(path.nodes()==0?from:path.lastNode());
            if (next.size() == 0)
                continue; // no new edges

            // Split path for other edges
            int splits=0;
            for (Edge edge:next)
            {
                if (seen.contains(edge))
                    continue;
                seen.add(edge);
                Path nextPath = (++splits==next.size())?path:path.forkPath();
                // Add segment to split'd path
                nextPath.add(edge);
                
                // Are we there yet?
                if (destination.equals(edge.getTo()))
                    return nextPath;

                edgesAdded = true;
                
                // Add to extra paths
                if (nextPath!=path)
                    paths.add(nextPath);
            }
        }

        if (edgesAdded)
            return breadthFirst(from,destination,paths,seen);
        return null;
    }
