    @Override
    public void doStart()
        throws Exception
    {
        super.doStart();

        if (!javax.servlet.Filter.class
            .isAssignableFrom(_class))
        {
            String msg = _class+" is not a javax.servlet.Filter";
            super.stop();
            throw new IllegalStateException(msg);
        }
    }
