    void setCorrelated() {

        if (dataExpression != null) {
            dataExpression.isCorrelated = true;
        }

        if (queryExpression != null) {
            queryExpression.isCorrelated = true;
        }
    }
