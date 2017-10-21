    void collectObjectNames(Set set) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].collectObjectNames(set);
            }
        }

        if (table != null) {
            if (table.queryExpression != null) {
                table.queryExpression.collectObjectNames(set);
            }
        }
    }
