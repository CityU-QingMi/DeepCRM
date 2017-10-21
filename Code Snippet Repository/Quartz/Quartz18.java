    @Override
    public Collection<SchedulerEntity> createSchedulerEntities(Set<String> schedulerNames, Set<String> attributes) {
        Collection<SchedulerEntity> schedulerEntities = new ArrayList<SchedulerEntity>();
        if (schedulerNames == null) {
            for (Entry<String, QuartzScheduler> scheduler : cacheManagerSamplerRepo.entrySet()) {
                SchedulerEntity schedulerEntity = new SchedulerEntity();
                schedulerEntity.setName(scheduler.getKey());
                schedulerEntity.setAgentId(AgentEntity.EMBEDDED_AGENT_ID);

                schedulerEntities.add(schedulerEntity);
            }
        }
        return schedulerEntities;
    }
