    public boolean containsCycle() {
        for (T v : graph) {
            if (!marks.containsKey(v)) {
                if (mark(v)) {
                    // return true;
                }
            }
        }
        // return false;
        return !verticesInCycles.isEmpty();
    }
