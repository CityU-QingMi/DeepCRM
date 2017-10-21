    void dropConstraint(Session session, HsqlName name, boolean cascade) {

        writeLock.lock();

        try {
            Table t = getUserTable(name.parent.name, name.parent.schema.name);
            TableWorks tw = new TableWorks(session, t);

            tw.dropConstraint(name.name, cascade);
        } finally {
            writeLock.unlock();
        }
    }
