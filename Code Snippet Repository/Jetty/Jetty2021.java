    public void doStop()
    {
        if (_runner != null)
        {
            _done = true;
            try
            {
                _runner.join();
            }
            catch (InterruptedException ex) {}
            
            _monitorInfo.clear();
        }
    }
