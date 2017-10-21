    protected String getContextName(ContextHandler context)
    {
        String name = null;
        
        if (context.getContextPath()!=null && context.getContextPath().length()>0)
        {
            int idx = context.getContextPath().lastIndexOf('/');
            name = idx < 0 ? context.getContextPath() : context.getContextPath().substring(++idx);
            if (name==null || name.length()==0)
                name= "ROOT";
        }
        
        if (name==null && context.getBaseResource()!=null)
        {
            try
            {
                if (context.getBaseResource().getFile()!=null)
                    name = context.getBaseResource().getFile().getName();
            }
            catch(IOException e)
            {
                LOG.ignore(e);
                name=context.getBaseResource().getName();
            }
        }
        
        if (context.getVirtualHosts()!=null && context.getVirtualHosts().length>0)
            name='"'+name+"@"+context.getVirtualHosts()[0]+'"';
        
        return name;
    }
