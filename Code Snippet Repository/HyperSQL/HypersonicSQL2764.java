    public void indexRow(Session session, Row row) {

        try {
            row = (Row) get(row, true);

            super.indexRow(session, row);
        } catch (HsqlException e) {
            throw e;
        } finally {
            row.keepInMemory(false);
        }
    }
