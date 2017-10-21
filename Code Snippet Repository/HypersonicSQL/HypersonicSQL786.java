    void dropIndex(Session session, HsqlName name) {

        writeLock.lock();

        try {
            Table t = getUserTable(name.parent.name, name.parent.schema.name);
            TableWorks tw = new TableWorks(session, t);

            tw.dropIndex(name.name);
        } finally {
            writeLock.unlock();
        }
    }
