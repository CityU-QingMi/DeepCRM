    @Override
    protected void doStop() throws Exception
    {
        _houseKeeper.stop();
        if (_ownHouseKeeper)
        {
            _houseKeeper = null;
        }
        _random = null;
    }
