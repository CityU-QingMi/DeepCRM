    public void makeTempDirectory (File parent, WebAppContext context)
            throws Exception
    {
        if (parent == null || !parent.exists() || !parent.canWrite() || !parent.isDirectory())
            throw new IllegalStateException("Parent for temp dir not configured correctly: "+(parent==null?"null":"writeable="+parent.canWrite()));

        //Create a name for the webapp     
        String temp = getCanonicalNameForWebAppTmpDir(context);
        File tmpDir = null;
        if (context.isPersistTempDirectory())
        {
            //if it is to be persisted, make sure it will be the same name
            //by not using File.createTempFile, which appends random digits
            tmpDir = new File (parent, temp);
        }
        else
        {
            //ensure file will always be unique by appending random digits
            tmpDir = File.createTempFile(temp, ".dir", parent);
            //delete the file that was created
            tmpDir.delete();
            //and make a directory of the same name
            tmpDir.mkdirs();
        }
        configureTempDirectory(tmpDir, context);

        if(LOG.isDebugEnabled())
            LOG.debug("Set temp dir "+tmpDir);
        context.setTempDirectory(tmpDir);
    }
