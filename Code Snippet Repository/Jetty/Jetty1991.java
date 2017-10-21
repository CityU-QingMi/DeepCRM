    protected  Resource unpackOverlay (WebAppContext context, Overlay overlay)
    throws IOException
    {
        LOG.debug("Unpacking overlay: " + overlay);
        
        if (overlay.getResource() == null)
            return null; //nothing to unpack
   
        //Get the name of the overlayed war and unpack it to a dir of the
        //same name in the temporary directory
        String name = overlay.getResource().getName();
        if (name.endsWith("!/"))
            name = name.substring(0,name.length()-2);
        int i = name.lastIndexOf('/');
        if (i>0)
            name = name.substring(i+1,name.length());
        name = name.replace('.', '_');
        name = name+(++COUNTER); //add some digits to ensure uniqueness
        File dir = new File(context.getTempDirectory(), name); 
        
        //if specified targetPath, unpack to that subdir instead
        File unpackDir = dir;
        if (overlay.getConfig() != null && overlay.getConfig().getTargetPath() != null)
            unpackDir = new File (dir, overlay.getConfig().getTargetPath());
        
        overlay.getResource().copyTo(unpackDir);
        //use top level of unpacked content
        Resource unpackedOverlay = Resource.newResource(dir.getCanonicalPath());
        
        LOG.debug("Unpacked overlay: "+overlay+" to "+unpackedOverlay);
        return  unpackedOverlay;
    }
