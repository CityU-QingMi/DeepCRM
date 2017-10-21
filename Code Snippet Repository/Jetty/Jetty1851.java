    public AttributeList setAttributes(AttributeList attrs)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("setAttributes");

        AttributeList results = new AttributeList(attrs.size());
        Iterator<Object> iter = attrs.iterator();
        while (iter.hasNext())
        {
            try
            {
                Attribute attr = (Attribute) iter.next();
                setAttribute(attr);
                results.add(new Attribute(attr.getName(), getAttribute(attr.getName())));
            }
            catch (Exception e)
            {
                LOG.warn(Log.EXCEPTION, e);
            }
        }
        return results;
    }
