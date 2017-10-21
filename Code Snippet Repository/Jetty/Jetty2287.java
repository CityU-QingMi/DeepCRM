    @Override
    public void destroy (WebAppContext context) throws Exception
    {
        try
        {
            //unbind any NamingEntries that were configured in this webapp's name space           
            NamingContext scopeContext = (NamingContext)NamingEntryUtil.getContextForScope(context);
            scopeContext.getParent().destroySubcontext(scopeContext.getName());
        }
        catch (NameNotFoundException e)
        {
            LOG.ignore(e);
            LOG.debug("No jndi entries scoped to webapp {}", context);
        }
        catch (NamingException e)
        {
            LOG.debug("Error unbinding jndi entries scoped to webapp "+context, e);
        }
    }
