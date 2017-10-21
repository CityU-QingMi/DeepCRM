    void removeDependentObjects(HsqlName name) {

        writeLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(name.schema.name);

            schema.indexLookup.removeParent(name);
            schema.constraintLookup.removeParent(name);
            schema.triggerLookup.removeParent(name);
        } finally {
            writeLock.unlock();
        }
    }
