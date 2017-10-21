    public void replaceReferences(SchemaObject oldObject,
                                  SchemaObject newObject) {

        writeLock.lock();

        try {
            removeReferencesFrom(oldObject);
            addReferencesFrom(newObject);
        } finally {
            writeLock.unlock();
        }
    }
