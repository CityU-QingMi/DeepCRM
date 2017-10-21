    public boolean hasSchemas(Grantee grantee) {

        readLock.lock();

        try {
            Iterator it = schemaMap.values().iterator();

            while (it.hasNext()) {
                Schema schema = (Schema) it.next();

                if (grantee.equals(schema.getOwner())) {
                    return true;
                }
            }

            return false;
        } finally {
            readLock.unlock();
        }
    }
