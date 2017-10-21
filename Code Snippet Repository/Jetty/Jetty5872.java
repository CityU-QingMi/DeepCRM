    @Before
    public void before()
    {
        _executions.clear();

        Producer producer = () ->
        {
            try
            {
                _producer=Thread.currentThread();
                Runnable task= _produce.take();
                if (task==NULLTASK)
                    return null;
                return task;
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
                return null;
            }
            finally
            {
                _producer=null;
            }
        };

        Executor executor = _executions::add;

        _ewyk = new ExecuteProduceConsume(producer,executor);
    }
