    void resolveTypesPartThree(Session session) {

        if (isResolved) {
            return;
        }

        sortAndSlice.setSortIndex(this);
        setRangeVariableConditions(session);
        setDistinctConditions(session);
        setAggregateConditions(session);
        sortAndSlice.setSortRange(this);

        for (int i = 0; i < rangeVariables.length; i++) {
            rangeVariables[i].resolveRangeTableTypes(session, rangeVariables);
        }

        setResultNullability();

        rangeVariableList = null;
        tempSet           = null;
        compileContext    = null;
        outerRanges       = null;
        isResolved        = true;
    }
