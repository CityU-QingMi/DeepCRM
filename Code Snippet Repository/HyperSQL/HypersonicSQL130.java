    Expression replaceColumnReferences(RangeVariable range,
                                       Expression[] list) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            nodes[i] = nodes[i].replaceColumnReferences(range, list);
        }

        if (table != null && table.queryExpression != null) {
            table.queryExpression.replaceColumnReferences(range, list);
        }

        return this;
    }
