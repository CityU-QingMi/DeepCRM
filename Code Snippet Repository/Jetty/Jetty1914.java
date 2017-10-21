    public static Context bind (Context ctx, String nameStr, Object obj)
        throws NamingException
    {
        Name name = ctx.getNameParser("").parse(nameStr);

        //no name, nothing to do
        if (name.size() == 0)
            return null;

        Context subCtx = ctx;

        //last component of the name will be the name to bind
        for (int i=0; i < name.size() - 1; i++)
        {
            try
            {
                subCtx = (Context)subCtx.lookup (name.get(i));
                if(__log.isDebugEnabled())
                    __log.debug("Subcontext "+name.get(i)+" already exists");
            }
            catch (NameNotFoundException e)
            {
                subCtx = subCtx.createSubcontext(name.get(i));
                if(__log.isDebugEnabled())
                    __log.debug("Subcontext "+name.get(i)+" created");
            }
        }

        subCtx.rebind (name.get(name.size() - 1), obj);
        if(__log.isDebugEnabled())
            __log.debug("Bound object to "+name.get(name.size() - 1));
        return subCtx;
    }
