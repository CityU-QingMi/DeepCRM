    private Uptime()
    {
        try
        {
            impl = new DefaultImpl();
        }
        catch (UnsupportedOperationException e)
        {
            System.err.printf("Defaulting Uptime to NOIMPL due to (%s) %s%n",e.getClass().getName(),e.getMessage());
            impl = null;
        }
    }
