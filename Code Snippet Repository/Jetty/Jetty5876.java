    @Test
    public void testProduceOneBlockingTaskIdleByTask() throws Exception
    {
        final Task t0 = new Task(true);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                _produce.add(t0);
                _produce.add(NULLTASK);
                _ewyk.produce();
            }
        };
        thread.start();

        // wait for execute thread to block in
        t0.awaitRun();

        // Should have dispatched only one helper
        Assert.assertEquals(_ewyk,_executions.poll());

        // unblock task
        t0.unblock();
        // will run to completion because are become idle
        thread.join();
        Assert.assertThat(_ewyk.isIdle(), Matchers.equalTo(true));

        // because we are idle, dispatched thread is noop
        _ewyk.run();
    }
