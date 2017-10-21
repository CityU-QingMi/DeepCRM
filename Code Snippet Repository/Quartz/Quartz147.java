    public List<String> getTriggerNames(String groupName) throws Exception {
        try {
            List<String> triggerNames = new ArrayList<String>();
            for(TriggerKey key: scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(groupName))) {
                triggerNames.add(key.getName());
            }
            return triggerNames;
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
