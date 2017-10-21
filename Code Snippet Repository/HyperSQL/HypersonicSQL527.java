    private void addUnresolvedExpressions(HsqlList expressions) {

        if (expressions == null) {
            return;
        }

        if (unresolvedExpressions == null) {
            unresolvedExpressions = new ArrayListIdentity();
        }

        unresolvedExpressions.addAll(expressions);
    }
