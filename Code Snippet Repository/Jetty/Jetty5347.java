    @Override
    public void produce()
    {
        try (Lock locked = _locker.lock())
        {
            switch(_state)
            {
                case IDLE:
                    _state=State.PRODUCE;
                    break;

                case PRODUCE:
                case EXECUTE:
                    _state=State.EXECUTE;
                    return;
            }
        }

        // Produce until we no task is found.
        while (true)
        {
            // Produce a task.
            Runnable task = _producer.produce();
            if (LOG.isDebugEnabled())
                LOG.debug("{} produced {}", _producer, task);

            if (task == null)
            {
                try (Lock locked = _locker.lock())
                {
                    switch(_state)
                    {
                        case IDLE:
                            throw new IllegalStateException();
                        case PRODUCE:
                            _state=State.IDLE;
                            return;
                        case EXECUTE:
                            _state=State.PRODUCE;
                            continue;
                    }
                }
            }

            // Execute the task.
            _executor.execute(task);
        }        
    }
