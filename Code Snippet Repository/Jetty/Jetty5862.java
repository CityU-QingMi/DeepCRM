    @Test
    public void testExecution() throws Exception
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
    }
