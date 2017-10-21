    void resetColumnReferences() {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            nodes[i].resetColumnReferences();
        }
    }
