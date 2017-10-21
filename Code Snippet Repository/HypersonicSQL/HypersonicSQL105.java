    boolean hasReference(RangeVariable range) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                if (nodes[i].hasReference(range)) {
                    return true;
                }
            }
        }

        if (table != null && table.queryExpression != null) {
            if (table.queryExpression.hasReference(range)) {
                return true;
            }
        }

        return false;
    }
