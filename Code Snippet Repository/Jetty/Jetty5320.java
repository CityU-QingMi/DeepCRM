    @Override
    public void doStop() throws Exception
    {
        if (_lease!=null)
            _lease.close();
        while(true)
        {
            ReservedThread thread = _stack.pop();
            if (thread==null)
            {
                super.doStop();
                return;
            }

            _size.decrementAndGet();

            thread.stop();
        }
    }
