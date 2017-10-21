    public void testPlugins() throws Exception {
        final StringBuffer result = new StringBuffer();
        
        SchedulerPlugin testPlugin = new SchedulerPlugin() {
            public void initialize(String name, org.quartz.Scheduler scheduler, ClassLoadHelper classLoadHelper) throws org.quartz.SchedulerException {
                result.append(name).append("|").append(scheduler.getSchedulerName());
            };
            public void start() {
                result.append("|start");
            };
            public void shutdown() {
                result.append("|shutdown");
            };
        };
        
        ThreadPool threadPool = new SimpleThreadPool(1, 5);
        threadPool.initialize();
        DirectSchedulerFactory.getInstance().createScheduler(
                "MyScheduler", "Instance1", threadPool,
                new RAMJobStore(), Collections.singletonMap("TestPlugin", testPlugin), 
                null, -1, 0, 0, false, null);
        
        Scheduler scheduler = DirectSchedulerFactory.getInstance().getScheduler("MyScheduler");
        scheduler.start();
        scheduler.shutdown();
        
        assertEquals("TestPlugin|MyScheduler|start|shutdown", result.toString());
    }
