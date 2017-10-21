    void setReturningResultSet() {

        if (unionCorresponding) {
            persistenceScope = TableBase.SCOPE_SESSION;

            return;
        }

        leftQueryExpression.setReturningResultSet();
    }
