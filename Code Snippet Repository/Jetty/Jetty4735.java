    public Object fromJSON(Map map)
    {
        if (!_fromJSON)
            throw new UnsupportedOperationException();
        try
        {
            Class c=Loader.loadClass((String)map.get("class"));
            return _valueOf.invoke(null,c,map.get("value"));
        }
        catch(Exception e)
        {
            LOG.warn(e);
        }
        return null;
    }
