    public static void stop(Object object)
    {
        if (object instanceof LifeCycle)
        {
            try
            {
                ((LifeCycle)object).stop();
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
