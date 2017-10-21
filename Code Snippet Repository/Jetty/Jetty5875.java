    @Test
    public void testProduceOneBlockingTaskIdleByDispatch() throws Exception
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
        Assert.assertEquals(thread,t0.getThread());

        // Should have dispatched only one helper
        Assert.assertEquals(_ewyk,_executions.poll());
        // which is make us idle
        _ewyk.run();
        Assert.assertThat(_ewyk.isIdle(), Matchers.equalTo(true));


        // unblock task
        t0.unblock();
        // will run to completion because are already idle
        thread.join();
    }
