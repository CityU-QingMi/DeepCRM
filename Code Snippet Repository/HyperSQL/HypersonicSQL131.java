    void replaceRangeVariables(RangeVariable[] ranges,
                               RangeVariable[] newRanges) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            nodes[i].replaceRangeVariables(ranges, newRanges);
        }

        if (table != null && table.queryExpression != null) {
            table.queryExpression.replaceRangeVariables(ranges, newRanges);
        }
    }
