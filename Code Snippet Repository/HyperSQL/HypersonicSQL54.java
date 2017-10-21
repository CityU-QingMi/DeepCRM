    void checkInsert(Session session, Table table, Object[] data,
                     boolean isNew) {

        switch (constType) {

            case SchemaObject.ConstraintTypes.CHECK :
                if (!isNotNull) {
                    checkCheckConstraint(session, table, data);
                }

                return;

            case SchemaObject.ConstraintTypes.FOREIGN_KEY :
                PersistentStore store = core.mainTable.getRowStore(session);

                if (ArrayUtil.hasNull(data, core.refCols)) {
                    if (core.matchType == OpTypes.MATCH_SIMPLE) {
                        return;
                    }

                    if (core.refCols.length == 1) {
                        return;
                    }

                    if (ArrayUtil.hasAllNull(data, core.refCols)) {
                        return;
                    }

                    // core.matchType == OpTypes.MATCH_FULL
                } else if (core.mainIndex.existsParent(session, store, data,
                                                       core.refCols)) {
                    return;
                }

                throw getException(data);
        }
    }
