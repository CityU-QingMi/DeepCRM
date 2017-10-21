    public HsqlArrayList getSchemas(Grantee grantee) {

        readLock.lock();

        try {
            HsqlArrayList list = new HsqlArrayList();
            Iterator      it   = schemaMap.values().iterator();

            while (it.hasNext()) {
                Schema schema = (Schema) it.next();

                if (grantee.equals(schema.getOwner())) {
                    list.add(schema);
                }
            }

            return list;
        } finally {
            readLock.unlock();
        }
    }
