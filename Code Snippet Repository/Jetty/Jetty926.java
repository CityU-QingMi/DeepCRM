    @Override
    public SessionDataStore getSessionDataStore(SessionHandler handler) throws Exception
    {
        GCloudSessionDataStore ds = new GCloudSessionDataStore();
        ds.setBackoffMs(getBackoffMs());
        ds.setMaxRetries(getMaxRetries());
        ds.setGracePeriodSec(getGracePeriodSec());
        ds.setNamespace(_namespace);
        ds.setSavePeriodSec(getSavePeriodSec());
        return ds;
    }
