    protected void set(String[] entries,  PathMap<IPAddressMap<Boolean>> patternMap)
    {
        patternMap.clear();

        if (entries != null && entries.length > 0)
        {
            for (String addrPath:entries)
            {
                add(addrPath, patternMap);
            }
        }
    }
