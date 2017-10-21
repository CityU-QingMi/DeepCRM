    public void teardown () throws Exception
    {
        _manager.removeCache(_name);
        if (_useFileStore)
        {
            if (_tmpdir != null)
            {
                IO.delete(_tmpdir);
            }
        }
    }
