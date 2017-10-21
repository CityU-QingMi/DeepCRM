    public void removeSchemaObjects(OrderedHashSet set) {

        writeLock.lock();

        try {
            for (int i = 0; i < set.size(); i++) {
                HsqlName name = (HsqlName) set.get(i);

                removeSchemaObject(name);
            }
        } finally {
            writeLock.unlock();
        }
    }
