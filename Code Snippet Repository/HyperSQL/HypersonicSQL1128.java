    public TableDerived newDerivedTable(Session session) {

        TableDerived td = this;

        if (isRecompiled()) {
            ParserDQL p = new ParserDQL(session, new Scanner(),
                                        session.parser.compileContext);

            p.reset(session, sql);
            p.read();
            p.compileContext.setCurrentSubquery(tableName.name);

            td = p.XreadSubqueryTableBody(tableName, OpTypes.TABLE_SUBQUERY);

            td.queryExpression.resolve(session,
                                       p.compileContext.getOuterRanges(),
                                       null);

            td.columnList   = columnList;
            td.columnCount  = columnList.size();
            td.triggerList  = triggerList;
            td.triggerLists = triggerLists;
            td.view         = view;

            td.createPrimaryKey();
        }

        return td;
    }
