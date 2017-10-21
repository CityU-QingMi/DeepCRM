    @ManagedAttribute(value="", readonly=true)
    @Override
    public boolean isPassivating()
    {
        //TODO run in the _context to ensure classloader is set
        try 
        {
           Class<?> remoteClass = Thread.currentThread().getContextClassLoader().loadClass("org.infinispan.client.hotrod.RemoteCache");
           if (remoteClass.isAssignableFrom(_cache.getClass()))
           {
               return true;
           }
           return false;
        }
        catch (ClassNotFoundException e)
        {
            return false;
        }
    }
