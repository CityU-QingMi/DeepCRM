    public Object getLazyMatches(String addr)
    {
        if (addr == null)
            return LazyList.getList(super.entrySet());
        
        Object entries = null;
        for(Map.Entry<String, TYPE> entry: super.entrySet())
        {
            if (_patterns.get(entry.getKey()).match(addr))
            {
                entries = LazyList.add(entries,entry);
            }
        }
        return entries;        
    }
