    boolean isCorrelated() {

        if (dataExpression != null) {
            return dataExpression.isCorrelated;
        }

        if (queryExpression != null) {
            return queryExpression.isCorrelated;
        }

        return false;
    }
