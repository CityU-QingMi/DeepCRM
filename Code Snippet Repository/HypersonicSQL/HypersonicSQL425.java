    TableDerived XreadJoinedTableAsSubqueryOrNull() {

        int position = getPosition();

        readThis(Tokens.OPENBRACKET);
        compileContext.incrementDepth();

        QuerySpecification qs = XreadJoinedTableAsView();

        qs.resolveReferences(session, compileContext.getOuterRanges());

        if (qs.rangeVariables.length < 2) {
            compileContext.decrementDepth();
            rewind(position);

            return null;
        }

        qs.resolveTypesPartOne(session);
        qs.resolveTypesPartTwo(session);

        TableDerived td = newSubQueryTable(qs, OpTypes.TABLE_SUBQUERY);

        readThis(Tokens.CLOSEBRACKET);
        td.setSQL(getLastPart(position));
        td.prepareTable(session);
        compileContext.decrementDepth();

        return td;
    }
