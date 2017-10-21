    public RowAction addDeleteAction(Session session, Table table,
                                     PersistentStore store, Row row,
                                     int[] colMap) {

        RowAction action;

        synchronized (row) {
            switch (table.tableType) {

                case TableBase.CACHED_TABLE :
                    action = RowAction.addDeleteAction(session, table, row,
                                                       colMap);

                    addTransactionInfo(row);
                    break;

                case TableBase.TEMP_TABLE :
                    action = RowAction.addDeleteAction(session, table, row,
                                                       colMap);

                    store.delete(session, row);

                    row.rowAction = null;
                    break;

                case TableBase.MEMORY_TABLE :
                default :
                    action = RowAction.addDeleteAction(session, table, row,
                                                       colMap);
            }
        }

        session.rowActionList.add(action);

        return action;
    }
