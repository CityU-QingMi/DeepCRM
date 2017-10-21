    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
            {new TimerScheduler()},
            {new ScheduledExecutorScheduler()}/*,
            {new ConcurrentScheduler(0)},
            {new ConcurrentScheduler(1500)},
            {new ConcurrentScheduler(executor,1500)}*/
        };
        return Arrays.asList(data);
    }
