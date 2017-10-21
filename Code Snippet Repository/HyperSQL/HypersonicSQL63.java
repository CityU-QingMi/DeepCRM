    void prepareCheckConstraint(Session session, Table table) {

        // to ensure no subselects etc. are in condition
        check.checkValidCheckConstraint();

        QuerySpecification checkSelect = Expression.getCheckSelect(session,
            table, check);

        rangeVariable = checkSelect.rangeVariables[0];

        // removes reference to the Index object in range variable
        rangeVariable.setForCheckConstraint();

        if (check.getType() == OpTypes.NOT
                && check.getLeftNode().getType() == OpTypes.IS_NULL
                && check.getLeftNode().getLeftNode().getType()
                   == OpTypes.COLUMN) {
            notNullColumnIndex =
                check.getLeftNode().getLeftNode().getColumnIndex();
            isNotNull = true;
        }
    }
