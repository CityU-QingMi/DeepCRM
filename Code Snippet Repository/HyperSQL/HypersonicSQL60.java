    void checkReferencedRows(Session session, Table table) {

        RowIterator it = table.rowIterator(session);

        while (it.next()) {
            Object[] rowData = it.getCurrent();

            checkInsert(session, table, rowData, false);
        }
    }
