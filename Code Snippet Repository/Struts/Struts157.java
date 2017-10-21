    public boolean edgeExists(T start, T end) {
        /* Confirm both endpoints exist. */
        if (!mGraph.containsKey(start)) {
            throw new NoSuchElementException("The start node does not exist in the graph.");
        } else if (!mGraph.containsKey(end)) {
            throw new NoSuchElementException("The end node does not exist in the graph.");
        }

        return mGraph.get(start).contains(end);
    }
