    TableDerived newSubQueryTable(HsqlName name, QueryExpression qe,
                                  int opType) {

        if (name == null) {
            name = database.nameManager.getSubqueryTableName();
        }

        TableDerived td = new TableDerived(database, name,
                                           TableBase.SYSTEM_SUBQUERY, qe,
                                           null, opType,
                                           compileContext.getDepth());

        return td;
    }
