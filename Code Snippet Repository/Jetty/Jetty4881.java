    public Map.Entry<String, TYPE> getMatch(String addr)
    {
        if (addr != null)
        {
            for(Map.Entry<String, TYPE> entry: super.entrySet())
            {
                if (_patterns.get(entry.getKey()).match(addr))
                {
                    return entry;
                }
            }
        }
        return null;
    }
