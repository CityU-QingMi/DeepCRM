    public void dropTableOrView(Session session, Table table,
                                boolean cascade) {

        writeLock.lock();

        try {
            if (table.isView()) {
                dropView(table, cascade);
            } else {
                dropTable(session, table, cascade);
            }
        } finally {
            writeLock.unlock();
        }
    }
