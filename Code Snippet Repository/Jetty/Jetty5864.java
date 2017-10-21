    @Test
    public void testQuickCancel() throws Exception
    {
        final AtomicLong executed = new AtomicLong();
        Scheduler.Task task=_scheduler.schedule(new Runnable()
        {
            @Override
            public void run()
            {
                executed.set(System.currentTimeMillis());
            }
        },2000,TimeUnit.MILLISECONDS);

        Thread.sleep(100);
        Assert.assertTrue(task.cancel());
        Thread.sleep(2500);
        Assert.assertEquals(0,executed.get());
    }
