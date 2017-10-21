    public static void bindToENC ()
    throws NamingException
    {
        Transaction txEntry = (Transaction)NamingEntryUtil.lookupNamingEntry(null, Transaction.USER_TRANSACTION);

        if ( txEntry != null )
        {
            txEntry.bindToComp();
        }
        else
        {
            throw new NameNotFoundException( USER_TRANSACTION + " not found" );
        }
    }
