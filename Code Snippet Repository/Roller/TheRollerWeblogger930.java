    public void testCache() throws Exception {
        URL url = new URL("http://cnn.com");
        SyndFeedInfo info = new SyndFeedInfo();
        info.setUrl(url);
        
        String testPlanetCache = WebloggerConfig.getProperty("cache.dir");
        assertNotNull("testPlanetCache not null", testPlanetCache);
        assertTrue("testPlanetCache not zero length", testPlanetCache.trim().length() > 0);
        
        File cacheDir = new File(testPlanetCache);
        if (!cacheDir.exists()) cacheDir.mkdirs();
        
        DiskFeedInfoCache cache =
                new DiskFeedInfoCache(WebloggerConfig.getProperty("cache.dir"));
        cache.setFeedInfo(info.getUrl(), info);
        
        SyndFeedInfo info2 = cache.getFeedInfo(url);
        assertNotNull(info2);
        assertEquals(url, info2.getUrl());
    }
