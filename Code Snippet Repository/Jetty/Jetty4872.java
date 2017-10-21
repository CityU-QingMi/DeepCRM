    public Object getLazyMatches(String host)
    {
        if (host == null)
            return LazyList.getList(super.entrySet());
        
        int idx = 0;
        String domain = host.trim();
        HashSet<String> domains = new HashSet<String>();
        do {
            domains.add(domain);
            if ((idx = domain.indexOf('.')) > 0)
            {
                domain = domain.substring(idx+1);
            }
        } while (idx > 0);
        
        Object entries = null;
        for(Map.Entry<String, TYPE> entry: super.entrySet())
        {
            if (domains.contains(entry.getKey()))
            {
                entries = LazyList.add(entries,entry);
            }
        }
       
        return entries;        
    }
