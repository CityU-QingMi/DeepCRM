    @SuppressWarnings("")
    protected String peekTriggers() {

        StringBuilder str = new StringBuilder();
        synchronized (lock) {
            for (TriggerWrapper triggerWrapper : triggersByKey.values()) {
                str.append(triggerWrapper.trigger.getKey().getName());
                str.append("/");
            }
        }
        str.append(" | ");

        synchronized (lock) {
            for (TriggerWrapper timeTrigger : timeTriggers) {
                str.append(timeTrigger.trigger.getKey().getName());
                str.append("->");
            }
        }

        return str.toString();
    }
