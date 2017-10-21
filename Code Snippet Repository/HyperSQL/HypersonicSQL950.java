    Result executeDeleteTruncateStatement(Session session) {

        PersistentStore store   = targetTable.getRowStore(session);
        RowIterator     it = targetTable.getPrimaryIndex().firstRow(store);
        boolean         hasData = false;

        for (int i = 0; i < targetTable.fkMainConstraints.length; i++) {
            if (targetTable.fkMainConstraints[i].getRef() != targetTable) {
                HsqlName tableName =
                    targetTable.fkMainConstraints[i].getRef().getName();
                Table refTable =
                    session.database.schemaManager.getUserTable(tableName);

                if (!refTable.isEmpty(session)) {
                    throw Error.error(ErrorCode.X_23504,
                                      refTable.getName().name);
                }
            }
        }

        try {
            while (it.next()) {
                Row row = it.getCurrentRow();

                session.addDeleteAction((Table) row.getTable(), store, row,
                                        null);

                hasData = true;
            }

            if (restartIdentity && targetTable.identitySequence != null) {
                targetTable.identitySequence.reset();
            }
        } finally {
            it.release();
        }

        if (!hasData) {
            session.addWarning(HsqlException.noDataCondition);
        }

        return Result.updateOneResult;
    }
