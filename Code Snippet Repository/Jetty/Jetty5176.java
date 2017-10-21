    @Override
    public void prevent(ClassLoader loader)
    {
        try
        {
            Class<?> clazz = Class.forName("sun.misc.GC");
            Method requestLatency = clazz.getMethod("requestLatency", new Class[] {long.class});
            requestLatency.invoke(null, Long.valueOf(Long.MAX_VALUE-1));
        }
        catch (ClassNotFoundException e)
        {
            LOG.ignore(e);
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
