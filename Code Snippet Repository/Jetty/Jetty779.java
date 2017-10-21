    @Override
    protected void doStop() throws Exception
    {
        if (_scanner!=null)
        {
            _scanner.stop();
            _scanner.removeListener(_scannerListener);
            _scanner = null;
        }
    }
