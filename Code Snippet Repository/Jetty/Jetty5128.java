    public static void start(Object object)
    {
        if (object instanceof LifeCycle)
        {
            try
            {
                ((LifeCycle)object).start();
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
