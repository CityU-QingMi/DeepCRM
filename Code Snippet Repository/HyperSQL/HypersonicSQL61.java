    void recompile(Session session, Table newTable) {

        check = getNewCheckExpression(session);

        // this workaround is here to stop LIKE optimisation (for proper scripting)
        QuerySpecification checkSelect = Expression.getCheckSelect(session,
            newTable, check);

        rangeVariable = checkSelect.rangeVariables[0];

        rangeVariable.setForCheckConstraint();
    }
