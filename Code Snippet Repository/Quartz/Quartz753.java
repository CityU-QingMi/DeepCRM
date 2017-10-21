    public void testThreadName() throws Throwable {
        DirectSchedulerFactory.getInstance().createVolatileScheduler(4);
        Scheduler scheduler = DirectSchedulerFactory.getInstance().getScheduler();
        QuartzScheduler qs = getField(scheduler, "sched");
        QuartzSchedulerResources qsr = getField(qs, "resources");
        ThreadPool tp = qsr.getThreadPool();
        List<?> list = getField(tp,"workers");
        Object workerThread = list.get(0);
        String workerThreadName = workerThread.toString();
        assertFalse(workerThreadName.contains("null"));
        assertTrue(workerThreadName.contains(scheduler.getSchedulerName()));

    }
