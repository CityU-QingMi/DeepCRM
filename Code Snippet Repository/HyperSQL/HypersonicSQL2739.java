    public void indexRow(Session session, Row row) {

        writeLock();

        try {
            row = (Row) get(row, true);

            super.indexRow(session, row);
        } catch (HsqlException e) {
            database.txManager.removeTransactionInfo(row);

            throw e;
        } finally {
            row.keepInMemory(false);
            writeUnlock();
        }
    }
