    public AttributeList getAttributes(String[] names)
    {
        AttributeList results = new AttributeList(names.length);
        for (int i = 0; i < names.length; i++)
        {
            try
            {
                results.add(new Attribute(names[i], getAttribute(names[i])));
            }
            catch (Exception e)
            {
                LOG.warn(Log.EXCEPTION, e);
            }
        }
        return results;
    }
