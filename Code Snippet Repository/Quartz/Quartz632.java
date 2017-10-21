    private Map<JobKey, List<MutableTrigger>> buildTriggersByFQJobNameMap(List<MutableTrigger> triggers) {
        
        Map<JobKey, List<MutableTrigger>> triggersByFQJobName = new HashMap<JobKey, List<MutableTrigger>>();
        
        for(MutableTrigger trigger: triggers) {
            List<MutableTrigger> triggersOfJob = triggersByFQJobName.get(trigger.getJobKey());
            if(triggersOfJob == null) {
                triggersOfJob = new LinkedList<MutableTrigger>();
                triggersByFQJobName.put(trigger.getJobKey(), triggersOfJob);
            }
            triggersOfJob.add(trigger);
        }

        return triggersByFQJobName;
    }
