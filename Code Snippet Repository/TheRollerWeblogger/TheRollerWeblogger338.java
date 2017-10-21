    public List<PlanetGroup> getGroups() {
        List<PlanetGroup> displayGroups = new ArrayList<PlanetGroup>();
        
        for (PlanetGroup planetGroup : getPlanet().getGroups()) {
            // The "all" group is considered a special group and cannot be
            // managed independently
            if (!planetGroup.getHandle().equals("all")) {
                displayGroups.add(planetGroup);
            }
        }
        return displayGroups;
    }
