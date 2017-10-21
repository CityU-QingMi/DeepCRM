    public void release() {

        writeLock.lock();

        try {
            Iterator it = schemaMap.values().iterator();

            while (it.hasNext()) {
                Schema schema = (Schema) it.next();

                schema.release();
            }
        } finally {
            writeLock.unlock();
        }
    }
