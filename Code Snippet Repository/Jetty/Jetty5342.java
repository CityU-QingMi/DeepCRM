    @Override
    public void produce()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} execute", this);

        boolean produce = false;
        try (Lock locked = _locker.lock())
        {
            // If we are idle and a thread is not producing
            if (_idle)
            {
                if (_producing)
                    throw new IllegalStateException();

                // Then this thread will do the producing
                produce = _producing = true;
                // and we are no longer idle
                _idle = false;
            }
            else
            {
                // Otherwise, lets tell the producing thread
                // that it should call produce again before going idle
                _execute = true;
            }
        }

        if (produce)
            produceConsume();
    }
