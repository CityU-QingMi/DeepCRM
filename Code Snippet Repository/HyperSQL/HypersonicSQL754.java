    public void dropSchemas(Session session, Grantee grantee,
                            boolean cascade) {

        writeLock.lock();

        try {
            HsqlArrayList list = getSchemas(grantee);
            Iterator      it   = list.iterator();

            while (it.hasNext()) {
                Schema schema = (Schema) it.next();

                dropSchema(session, schema.getName().name, cascade);
            }
        } finally {
            writeLock.unlock();
        }
    }
