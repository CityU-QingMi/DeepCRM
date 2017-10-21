    protected void dump(StringBuilder buf, PathMap<IPAddressMap<Boolean>> patternMap)
    {
        for (String path: patternMap.keySet())
        {
            for (String addr: patternMap.get(path).keySet())
            {
                buf.append("# ");
                buf.append(addr);
                buf.append("|");
                buf.append(path);
                buf.append("\n");
            }
        }
    }
