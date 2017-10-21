    boolean isEquivalent(QueryExpression other) {

        if (!(other instanceof QuerySpecification)) {
            return false;
        }

        QuerySpecification otherSpec = (QuerySpecification) other;

        if (!Expression.equals(exprColumns, otherSpec.exprColumns)) {
            return false;
        }

        if (!Expression.equals(queryCondition, otherSpec.queryCondition)) {
            return false;
        }

        if (rangeVariables.length != otherSpec.rangeVariables.length) {
            return false;
        }

        for (int i = 0; i < rangeVariables.length; i++) {
            if (rangeVariables[i].getTable()
                    != otherSpec.rangeVariables[i].getTable()) {
                return false;
            }
        }

        return true;
    }
