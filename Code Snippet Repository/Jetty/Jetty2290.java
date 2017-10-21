    public void bindUserTransaction (WebAppContext context)
    throws Exception
    {
        try
        {
           Transaction.bindToENC();
        }
        catch (NameNotFoundException e)
        {
            LOG.debug("No Transaction manager found - if your webapp requires one, please configure one.");
        }
    }
