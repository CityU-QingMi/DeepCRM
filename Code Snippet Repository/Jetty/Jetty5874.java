    @Test
    public void testProduceManyNonBlockingTask()
    {
        Task[] tasks = new Task[10];
        for (int i=0;i<tasks.length;i++)
        {
            tasks[i]=new Task();
            _produce.add(tasks[i]);
        }
        _produce.add(NULLTASK);
        _ewyk.produce();

        for (Task task : tasks)
            Assert.assertThat(task.hasRun(), Matchers.equalTo(true));
        Assert.assertEquals(_ewyk,_executions.poll());
    }
