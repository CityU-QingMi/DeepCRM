    @Test
    public void testProduceOneNonBlockingTask()
    {
        Task t0 = new Task();
        _produce.add(t0);
        _produce.add(NULLTASK);
        _ewyk.produce();
        Assert.assertThat(t0.hasRun(), Matchers.equalTo(true));
        Assert.assertEquals(_ewyk,_executions.poll());
    }
