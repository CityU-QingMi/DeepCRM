    @Override
    public synchronized void doStop()
    {
        if (_running)
        {
            _running = false; 
            if (_timer!=null)
                _timer.cancel();
            if (_task!=null)
                _task.cancel();
            _task=null;
            _timer=null;
        }
    }
