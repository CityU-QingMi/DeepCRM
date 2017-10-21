    @Override
    public SessionDataStore getSessionDataStore (SessionHandler handler) throws Exception
    {
        InfinispanSessionDataStore store = new InfinispanSessionDataStore();
        store.setGracePeriodSec(getGracePeriodSec());
        store.setInfinispanIdleTimeoutSec(getInfinispanIdleTimeoutSec());
        store.setCache(getCache());
        store.setSavePeriodSec(getSavePeriodSec());
        return store;
    }
