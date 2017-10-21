    protected void parseJar (Set<? extends Handler> handlers, Resource jarResource) throws Exception
    {
        if (jarResource == null)
            return;
       
        if (jarResource.toString().endsWith(".jar"))
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Scanning jar {}", jarResource);

            MultiException me = new MultiException();
            // TODO do not force version 8 once ASM can scan 9
            MultiReleaseJarFile jarFile = new MultiReleaseJarFile(jarResource.getFile(),8,false);
            jarFile.stream().forEach(e->
            {
                try
                {
                    parseJarEntry(handlers, jarResource, e);
                }
                catch (Exception ex)
                {
                    me.add(new RuntimeException("Error scanning entry " + e.getName() + " from jar " + jarResource, ex));
                }
            });

            
            me.ifExceptionThrow();
        }        
    }
