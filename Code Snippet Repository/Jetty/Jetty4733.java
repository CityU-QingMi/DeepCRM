    public Object fromJSON(Map map)
    {
        if (!_fromJSON)
            throw new UnsupportedOperationException();
        try
        {
            synchronized(_format)
            {
                return _format.parseObject((String)map.get("value"));
            }
        }
        catch(Exception e)
        {
            LOG.warn(e);
        }
        return null;
    }
