    private boolean isReferenced(HsqlName object) {

        writeLock.lock();

        try {
            return referenceMap.containsKey(object);
        } finally {
            writeLock.unlock();
        }
    }
