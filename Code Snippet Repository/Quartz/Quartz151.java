    public List<CompositeData> getAllTriggers() throws Exception {
        try {
            List<Trigger> triggerList = new ArrayList<Trigger>();
            for (String triggerGroupName : scheduler.getTriggerGroupNames()) {
                for (TriggerKey triggerKey : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupName))) {
                    triggerList.add(scheduler.getTrigger(triggerKey));
                }
            }
            return TriggerSupport.toCompositeList(triggerList);
        } catch (Exception e) {
            throw newPlainException(e);
        }
    }
