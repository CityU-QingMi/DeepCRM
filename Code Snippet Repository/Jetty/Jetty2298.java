    public void bindEnvEntry(String name, Object value) throws Exception
    {
        InitialContext ic = null;
        boolean bound = false;
        //check to see if we bound a value and an EnvEntry with this name already
        //when we processed the server and the webapp's naming environment
        //@see EnvConfiguration.bindEnvEntries()
        ic = new InitialContext();
        try
        {
            NamingEntry ne = (NamingEntry)ic.lookup("java:comp/env/"+NamingEntryUtil.makeNamingEntryName(ic.getNameParser(""), name));
            if (ne!=null && ne instanceof EnvEntry)
            {
                EnvEntry ee = (EnvEntry)ne;
                bound = ee.isOverrideWebXml();
            }
        }
        catch (NameNotFoundException e)
        {
            bound = false;
        }

        if (!bound)
        {
            //either nothing was bound or the value from web.xml should override
            Context envCtx = (Context)ic.lookup("java:comp/env");
            NamingUtil.bind(envCtx, name, value);
        }
    }
