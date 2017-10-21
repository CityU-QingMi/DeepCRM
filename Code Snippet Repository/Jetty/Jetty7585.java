    @Override
    public Set<String> doGetExpired(Set<String> candidates)
    {
       HashSet<String> set = new HashSet<>();
        long now = System.currentTimeMillis();
        
       
        for (SessionData d:_map.values())
        {
            if (d.getExpiry() > 0 && d.getExpiry() <= now)
                set.add(d.getId());
        }
        return set;
        
        //return Collections.emptySet();
    }
