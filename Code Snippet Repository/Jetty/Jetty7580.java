    public TestServer(int port, int maxInactivePeriod, int scavengePeriod,  SessionCacheFactory cacheFactory, SessionDataStoreFactory storeFactory) throws Exception
    {
        _server = new Server(port);
        _maxInactivePeriod = maxInactivePeriod;
        _scavengePeriod = scavengePeriod;
        _cacheFactory = cacheFactory;
        _storeFactory = storeFactory;
        _contexts = new ContextHandlerCollection();
        _sessionIdManager = newSessionIdManager();
        _server.setSessionIdManager(_sessionIdManager);
        ((DefaultSessionIdManager) _sessionIdManager).setServer(_server);
        _housekeeper = new HouseKeeper();
        _housekeeper.setIntervalSec(_scavengePeriod);
        ((DefaultSessionIdManager)_sessionIdManager).setSessionHouseKeeper(_housekeeper);
    }
