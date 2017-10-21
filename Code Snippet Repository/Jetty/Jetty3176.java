    @Override
    public SessionDataStore getSessionDataStore(SessionHandler handler)
    {
        FileSessionDataStore fsds = new FileSessionDataStore();
        fsds.setDeleteUnrestorableFiles(isDeleteUnrestorableFiles());
        fsds.setStoreDir(getStoreDir());
        fsds.setGracePeriodSec(getGracePeriodSec());
        fsds.setSavePeriodSec(getSavePeriodSec());
        return fsds;
    }
