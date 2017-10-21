    public void testSetter() throws SchedulerException, IOException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/org/quartz/quartz.properties"));
        props.setProperty(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, MyThreadPool.class.getName());
        props.setProperty(StdSchedulerFactory.PROP_JOB_STORE_CLASS, MyJobStore.class.getName());
        
        StdSchedulerFactory factory = new StdSchedulerFactory(props);
        factory.getScheduler(); // this will initialize all the test fixtures.
        
        assertEquals(3, instanceIdCalls.get());
        assertEquals(3, instanceNameCalls.get());

        DirectSchedulerFactory directFactory = DirectSchedulerFactory.getInstance();
        directFactory.createScheduler(new MyThreadPool(), new MyJobStore());

        assertEquals(5, instanceIdCalls.get());
        assertEquals(6, instanceNameCalls.get());
    }
