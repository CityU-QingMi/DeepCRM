    public List<Subscription> getRankedSubscriptions(String groupHandle, int sinceDays, int length) {
        List<Subscription> list = new ArrayList<Subscription>();
        try {
            PlanetManager planetManager = WebloggerFactory.getWeblogger().getPlanetManager();
            Planet defaultPlanet = planetManager.getWeblogger(DEFAULT_PLANET_HANDLE);
            PlanetGroup planetGroup = planetManager.getGroup(defaultPlanet, groupHandle);
            List<Subscription> subs = planetManager.getTopSubscriptions(planetGroup, 0, length);
            for (Subscription sub : subs) {
                // TODO needs pojo wrapping from planet
                list.add(sub);
            }
        } catch (Exception e) {
            log.error("ERROR: get ranked blogs", e);
        }
        return list;
    }
