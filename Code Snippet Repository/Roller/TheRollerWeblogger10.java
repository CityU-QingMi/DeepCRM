    private FeedFetcherCache getRomeFetcherCache() {
        
        String cacheDirPath = WebloggerConfig.getProperty("cache.dir");
        
        // can't continue without cache dir
        if (cacheDirPath == null) {
            log.warn("Planet cache directory not set, feeds cannot be cached.");
            return null;
        }
        
        // allow ${user.home} in cache dir property
        String cacheDirName = cacheDirPath.replaceFirst(
                "\\$\\{user.home}",System.getProperty("user.home"));
        
        // allow ${catalina.home} in cache dir property
        if (System.getProperty("catalina.home") != null) {
            cacheDirName = cacheDirName.replaceFirst(
                    "\\$\\{catalina.home}",System.getProperty("catalina.home"));
        }
        
        // create cache  dir if it does not exist
        File cacheDir = null;
        try {
            cacheDir = new File(cacheDirName);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
        } catch (Exception e) {
            log.error("Unable to create planet cache directory: " +
                    ((cacheDir != null) ? cacheDir.getPath() : null), e);
            return null;
        }
        
        // abort if cache dir is not writable
        if (!cacheDir.canWrite()) {
            log.error("Planet cache directory is not writable: " + cacheDir.getPath());
            return null;
        }
        
        return new DiskFeedInfoCache(cacheDirName);
    }
