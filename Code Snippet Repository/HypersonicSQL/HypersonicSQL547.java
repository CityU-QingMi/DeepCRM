    private void resolveAggregates() {

        tempSet.clear();

        if (isAggregated) {
            aggregateCheck = new boolean[indexStartAggregates];

            tempSet.addAll(aggregateSet);

            indexLimitData = indexLimitExpressions = exprColumns.length
                    + tempSet.size();
            exprColumns = (Expression[]) ArrayUtil.resizeArray(exprColumns,
                    indexLimitExpressions);

            for (int i = indexStartAggregates, j = 0;
                    i < indexLimitExpressions; i++, j++) {
                Expression e = (Expression) tempSet.get(j);

                exprColumns[i]          = e.duplicate();
                exprColumns[i].nodes    = e.nodes;    // keep original nodes
                exprColumns[i].dataType = e.dataType;
            }

            tempSet.clear();
        }
    }
