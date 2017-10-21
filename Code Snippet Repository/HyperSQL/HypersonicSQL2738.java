    public void delete(Session session, Row row) {

        writeLock();

        try {
            super.delete(session, row);
        } finally {
            writeUnlock();
        }
    }
