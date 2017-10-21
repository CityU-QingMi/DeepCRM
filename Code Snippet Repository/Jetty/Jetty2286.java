    @Override
    public void deconfigure (WebAppContext context) throws Exception
    {
        //get rid of any bindings for comp/env for webapp
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(context.getClassLoader());
        ContextFactory.associateClassLoader(context.getClassLoader());
        try
        {
            Context ic = new InitialContext();
            Context compCtx =  (Context)ic.lookup ("java:comp");
            compCtx.destroySubcontext("env");

            //unbind any NamingEntries that were configured in this webapp's name space
            @SuppressWarnings("unchecked")
            List<Bound> bindings = (List<Bound>)context.getAttribute(JETTY_ENV_BINDINGS);
            context.setAttribute(JETTY_ENV_BINDINGS,null);
            if (bindings!=null)
            {
                Collections.reverse(bindings);
                for (Bound b:bindings)
                    b._context.destroySubcontext(b._name);
            }
        }
        catch (NameNotFoundException e)
        {
            LOG.warn(e);
        }
        finally
        {
            ContextFactory.disassociateClassLoader();
            Thread.currentThread().setContextClassLoader(oldLoader);
        }
    }
