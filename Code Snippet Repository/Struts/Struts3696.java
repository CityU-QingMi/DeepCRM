    public SubGraph create(String namespace) {
        if (namespace.equals("")) {
            return this;
        }

        String[] parts = namespace.split("/");
        SubGraph last = this;
        for (String part : parts) {
            if (part.equals("")) {
                continue;
            }

            SubGraph subGraph = findSubGraph(part);
            if (subGraph == null) {
                subGraph = new SubGraph(part);
                last.addSubGraph(subGraph);
            }

            last = subGraph;
        }

        return last;
    }
