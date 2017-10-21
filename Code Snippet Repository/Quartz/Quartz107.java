    public void addInternalTriggerListener(TriggerListener triggerListener) {
        if (triggerListener.getName() == null
                || triggerListener.getName().length() == 0) {
            throw new IllegalArgumentException(
                    "TriggerListener name cannot be empty.");
        }

        synchronized (internalTriggerListeners) {
            internalTriggerListeners.put(triggerListener.getName(), triggerListener);
        }
    }
