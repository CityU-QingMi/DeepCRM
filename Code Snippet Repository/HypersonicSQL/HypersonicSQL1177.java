    public void addInsertAction(Session session, Table table,
                                PersistentStore store, Row row,
                                int[] changedColumns) {

        RowAction action = row.rowAction;

        if (action == null) {
/**/
/**/
/**/
/**/
            throw Error.runtimeError(ErrorCode.GENERAL_ERROR,
                                     "null insert action ");
        }

        store.indexRow(session, row);

        if (table.persistenceScope == Table.SCOPE_ROUTINE) {
            row.rowAction = null;

            return;
        }

        session.rowActionList.add(action);

        row.rowAction = null;
    }
