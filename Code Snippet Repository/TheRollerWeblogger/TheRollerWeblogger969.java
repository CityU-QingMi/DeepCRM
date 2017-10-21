    public void testRefreshEntries() {
        try {      
            PlanetManager planet = WebloggerFactory.getWeblogger().getPlanetManager();
            
            // run sync task to fill aggregator with websites created by super
            SyncWebsitesTask syncTask = new SyncWebsitesTask();
            syncTask.init();
            syncTask.runTask();
            
            Planet planetObject = planet.getWebloggerById("zzz_default_planet_zzz");
            assertNotNull(planetObject);
            PlanetGroup group = planet.getGroup(planetObject, "all");
            assertEquals(1, group.getSubscriptions().size());

            RefreshRollerPlanetTask refreshTask = new RefreshRollerPlanetTask();
            refreshTask.runTask();
            
            planetObject = planet.getWeblogger("default");
            group = planet.getGroup(planetObject, "all");
            List agg = planet.getEntries(group, 0, -1);
            assertEquals(3, agg.size());
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
