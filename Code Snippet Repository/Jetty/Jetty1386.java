    public Map<String, String> getPathParams(String path)
    {
        Matcher matcher = getMatcher(path);
        if (matcher.matches())
        {
            if (group == PathSpecGroup.EXACT)
            {
                return Collections.emptyMap();
            }
            Map<String, String> ret = new HashMap<>();
            int groupCount = matcher.groupCount();
            for (int i = 1; i <= groupCount; i++)
            {
                ret.put(this.variables[i - 1],matcher.group(i));
            }
            return ret;
        }
        return null;
    }
