    @Test
    public void testLongCancel() throws Exception
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

        Thread.sleep(1600);
        Assert.assertTrue(task.cancel());
        Thread.sleep(1000);
        Assert.assertEquals(0,executed.get());
    }
