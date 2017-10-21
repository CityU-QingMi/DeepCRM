    public void run() {

        while (keepGoing) {
            TriggerData triggerData = popPair();

            if (triggerData != null) {
                if (triggerData.username != null) {
                    trigger.fire(this.triggerType, name.name,
                                 table.getName().name, triggerData.oldRow,
                                 triggerData.newRow);
                }
            }
        }

        try {
            thread.setContextClassLoader(null);
        } catch (Throwable t) {}
    }
