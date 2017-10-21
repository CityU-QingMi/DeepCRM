    private boolean mark(T vertex) {
/**/
/**/
/**/
        List<T> localCycles = new ArrayList<T>();
        marks.put(vertex, Status.MARKED);
        for (T u : graph.edgesFrom(vertex)) {
            if (marks.get(u) == Status.MARKED) {
                localCycles.add(vertex);
                // return true;
            } else if (!marks.containsKey(u)) {
                if (mark(u)) {
                    localCycles.add(vertex);
                    // return true;
                }
            }
        }
        marks.put(vertex, Status.COMPLETE);
        // return false;
        verticesInCycles.addAll(localCycles);
        return !localCycles.isEmpty();
    }
