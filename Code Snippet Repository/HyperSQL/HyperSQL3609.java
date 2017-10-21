    private void initialise() {

        firstLeftJoinIndex    = rangeVariables.length;
        firstRightJoinIndex   = rangeVariables.length;
        firstLateralJoinIndex = rangeVariables.length;
        firstOuterJoinIndex   = rangeVariables.length;
        inExpressions         = new Expression[rangeVariables.length];
        inInJoin              = new boolean[rangeVariables.length];
        tempJoinExpressions   = new HsqlArrayList[rangeVariables.length];

        for (int i = 0; i < rangeVariables.length; i++) {
            tempJoinExpressions[i] = new HsqlArrayList();
        }

        joinExpressions = new HsqlArrayList[rangeVariables.length];

        for (int i = 0; i < rangeVariables.length; i++) {
            joinExpressions[i] = new HsqlArrayList();
        }

        whereExpressions = new HsqlArrayList[rangeVariables.length];

        for (int i = 0; i < rangeVariables.length; i++) {
            whereExpressions[i] = new HsqlArrayList();
        }

        queryConditions.clear();
    }
