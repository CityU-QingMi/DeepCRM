    public boolean removeTriggers(List<TriggerKey> triggerKeys)
            throws JobPersistenceException {
        boolean allFound = true;

        synchronized (lock) {
            for(TriggerKey key: triggerKeys)
                allFound = removeTrigger(key) && allFound;
        }

        return allFound;
    }
