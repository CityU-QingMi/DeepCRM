    @Override
    protected void doStart() throws Exception
    {
        if (_server == null)
            throw new IllegalStateException ("No Server for SessionIdManager");

        initRandom();

        if (_workerName == null)
        {
            String inst = System.getenv("JETTY_WORKER_INSTANCE");
            _workerName = "node"+ (inst==null?"0":inst);
        }
        
        LOG.info("DefaultSessionIdManager workerName={}",_workerName);
        _workerAttr=(_workerName!=null && _workerName.startsWith("$"))?_workerName.substring(1):null;

        if (_houseKeeper == null)
        {
            LOG.info("No SessionScavenger set, using defaults");
            _ownHouseKeeper = true;
            _houseKeeper = new HouseKeeper();
            _houseKeeper.setSessionIdManager(this);
            addBean(_houseKeeper,true);
        }

        _houseKeeper.start();
    }
