    public void addEdge(T start, T dest) {
        /* Confirm both endpoints exist. */
        if (!mGraph.containsKey(start)) {
            throw new NoSuchElementException("The start node does not exist in the graph.");
        } else if (!mGraph.containsKey(dest)) {
            throw new NoSuchElementException("The destination node does not exist in the graph.");
        }

        /* Add the edge. */
        mGraph.get(start).add(dest);
    }
