    public void pauseAll() {

        synchronized (lock) {
            List<String> names = getTriggerGroupNames();

            for (String name: names) {
                pauseTriggers(GroupMatcher.triggerGroupEquals(name));
            }
        }
    }
