    @Override
    public void deconfigure(WebAppContext context) throws Exception
    {
        //if we're not persisting the temp dir contents delete it
        if (!context.isPersistTempDirectory())
        {
           IO.delete(context.getTempDirectory());
        }
        
        //if it wasn't explicitly configured by the user, then unset it
       Boolean tmpdirConfigured = (Boolean)context.getAttribute(TEMPDIR_CONFIGURED);
        if (tmpdirConfigured != null && !tmpdirConfigured) 
            context.setTempDirectory(null);

        //reset the base resource back to what it was before we did any unpacking of resources
        if (context.getBaseResource() != null)
            context.getBaseResource().close();
        context.setBaseResource(_preUnpackBaseResource);
    }
