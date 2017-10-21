    @Test
    public void testSchedulerMetaData() throws Exception{
        SchedulerMetaData remoteSchedulerMetaData = remoteScheduler.getMetaData();
        SchedulerMetaData metaData = scheduler.getMetaData();
        assertThat(remoteSchedulerMetaData.getSchedulerName(), equalTo(metaData.getSchedulerName()));
        assertThat(remoteSchedulerMetaData.getSchedulerInstanceId(), equalTo(metaData.getSchedulerInstanceId()));
        assertThat(remoteSchedulerMetaData.isInStandbyMode(), is(metaData.isInStandbyMode()));
        assertThat(remoteSchedulerMetaData.getSchedulerClass(), equalTo((Class)TestRemoteScheduler.class));
        assertThat(remoteSchedulerMetaData.isSchedulerRemote(), is(true));
        assertThat(remoteSchedulerMetaData.isStarted(), is(false)); // information not available through JMX
        assertThat(remoteSchedulerMetaData.isInStandbyMode(), is(metaData.isInStandbyMode()));
        assertThat(remoteSchedulerMetaData.isShutdown(), is(metaData.isShutdown()));
        assertThat(remoteSchedulerMetaData.getRunningSince(), nullValue()); // Information not available through JMX
        assertThat(remoteSchedulerMetaData.getNumberOfJobsExecuted(), is(metaData.getNumberOfJobsExecuted()));
        assertThat(remoteSchedulerMetaData.getJobStoreClass(), equalTo((Class)metaData.getJobStoreClass()));
        assertThat(remoteSchedulerMetaData.isJobStoreSupportsPersistence(), is(false)); // Information not available through JMX
        assertThat(remoteSchedulerMetaData.isJobStoreClustered(), is(false)); // Information not available through JMX
        assertThat(remoteSchedulerMetaData.getThreadPoolClass(), equalTo((Class)metaData.getThreadPoolClass()));
        assertThat(remoteSchedulerMetaData.getThreadPoolSize(), is(metaData.getThreadPoolSize()));
        assertThat(remoteSchedulerMetaData.getVersion(), equalTo(metaData.getVersion()));
        assertThat(remoteSchedulerMetaData.getJobStoreClass(), equalTo((Class)metaData.getJobStoreClass()));
    }
