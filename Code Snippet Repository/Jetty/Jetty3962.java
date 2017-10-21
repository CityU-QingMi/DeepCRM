    protected void purgeEntries (Field properties)
    throws IllegalArgumentException, IllegalAccessException
    {
        if (properties == null)
            return;

        if (!properties.isAccessible())
            properties.setAccessible(true);

        Map map = (Map) properties.get(null);
        if (map == null)
            return;

        Iterator<Class<?>> itor = map.keySet().iterator();
        while (itor.hasNext())
        {
            Class<?> clazz = itor.next();
            if (LOG.isDebugEnabled())
                LOG.debug("Clazz: "+clazz+" loaded by "+clazz.getClassLoader());
            if (Thread.currentThread().getContextClassLoader().equals(clazz.getClassLoader()))
            {
                itor.remove();
                if (LOG.isDebugEnabled())
                    LOG.debug("removed");
            }
            else
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("not removed: "+"contextclassloader="+Thread.currentThread().getContextClassLoader()+"clazz's classloader="+clazz.getClassLoader());
            }
        }
    }
