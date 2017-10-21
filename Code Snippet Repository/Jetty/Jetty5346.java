    @Override
    public void produce()
    {
        try (Locker.Lock lock = _locker.lock())
        {
            switch(_state)
            {
                case IDLE:
                    _state= State.PRODUCE;
                    break;

                case PRODUCE:
                case EXECUTE:
                    _state= State.EXECUTE;
                    return;
            }
        }

        // Iterate until we are complete.
        while (true)
        {
            // Produce a task.
            Runnable task = _producer.produce();
            if (LOG.isDebugEnabled())
                LOG.debug("{} produced {}", _producer, task);

            if (task == null)
            {
                try (Locker.Lock lock = _locker.lock())
                {
                    switch(_state)
                    {
                        case IDLE:
                            throw new IllegalStateException();
                        case PRODUCE:
                            _state= State.IDLE;
                            return;
                        case EXECUTE:
                            _state= State.PRODUCE;
                            continue;
                    }
                }
            }

            // Run the task.
            Invocable.invokePreferNonBlocking(task);
        }
    }
