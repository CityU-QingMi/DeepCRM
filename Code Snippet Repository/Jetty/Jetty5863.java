    @Test
    public void testTwoExecution() throws Exception
    {
        final AtomicLong executed = new AtomicLong();
        long expected=System.currentTimeMillis()+1000;
        Scheduler.Task task=_scheduler.schedule(new Runnable()
        {
            @Override
            public void run()
            {
                executed.set(System.currentTimeMillis());
            }
        },1000,TimeUnit.MILLISECONDS);

        Thread.sleep(1500);
        Assert.assertFalse(task.cancel());
        Assert.assertThat(executed.get(),Matchers.greaterThanOrEqualTo(expected));
        Assert.assertThat(expected-executed.get(),Matchers.lessThan(1000L));

        final AtomicLong executed1 = new AtomicLong();
        long expected1=System.currentTimeMillis()+1000;
        Scheduler.Task task1=_scheduler.schedule(new Runnable()
        {
            @Override
            public void run()
            {
                executed1.set(System.currentTimeMillis());
            }
        },1000,TimeUnit.MILLISECONDS);

        Thread.sleep(1500);
        Assert.assertFalse(task1.cancel());
        Assert.assertThat(executed1.get(),Matchers.greaterThanOrEqualTo(expected1));
        Assert.assertThat(expected1-executed1.get(),Matchers.lessThan(1000L));
    }
