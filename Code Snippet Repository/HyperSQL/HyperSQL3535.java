    public void collectObjectNames(Set set) {

        for (int i = 0; i < indexStartAggregates; i++) {
            exprColumns[i].collectObjectNames(set);
        }

        if (queryCondition != null) {
            queryCondition.collectObjectNames(set);
        }

        if (havingCondition != null) {
            havingCondition.collectObjectNames(set);
        }

        for (int i = 0, len = rangeVariables.length; i < len; i++) {
            HsqlName name = rangeVariables[i].getTable().getName();

            set.add(name);
        }
    }
