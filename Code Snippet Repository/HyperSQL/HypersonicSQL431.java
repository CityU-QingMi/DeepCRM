    Expression XreadInValueListConstructor(int degree) {

        int position = getPosition();

        compileContext.incrementDepth();

        Expression   e  = XreadInValueList(degree);
        TableDerived td = newSubQueryTable(e, OpTypes.IN);

        td.setSQL(getLastPart(position));

        e.table = td;

        compileContext.decrementDepth();

        return e;
    }
