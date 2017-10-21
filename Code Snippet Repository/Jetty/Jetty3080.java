    protected void add(String entry, PathMap<IPAddressMap<Boolean>> patternMap)
    {
        if (entry != null && entry.length() > 0)
        {
            boolean deprecated = false;
            int idx;
            if (entry.indexOf('|') > 0 )
            {
                idx = entry.indexOf('|');
            }
            else
            {
                idx = entry.indexOf('/');
                deprecated = (idx >= 0);
            }

            String addr = idx > 0 ? entry.substring(0,idx) : entry;
            String path = idx > 0 ? entry.substring(idx) : "/*";

            if (addr.endsWith("."))
                deprecated = true;
            if (path!=null && (path.startsWith("|") || path.startsWith("/*.")))
                path=path.substring(1);

            IPAddressMap<Boolean> addrMap = patternMap.get(path);
            if (addrMap == null)
            {
                addrMap = new IPAddressMap<Boolean>();
                patternMap.put(path,addrMap);
            }
            if (addr != null && !"".equals(addr))
                // MUST NOT BE null
                addrMap.put(addr, true);

            if (deprecated)
                LOG.debug(toString() +" - deprecated specification syntax: "+entry);
        }
    }
