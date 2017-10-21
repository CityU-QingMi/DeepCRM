    public void execute() throws MojoExecutionException, MojoFailureException
    {
        if (getLog().isDebugEnabled())
        {

            getLog().info("webAppSourceDirectory=" + webAppSourceDirectory);
            getLog().info("generatedClasses=" + generatedClasses);
            getLog().info("webXmlFragment=" + webXmlFragment);
            getLog().info("webXml="+webXml);
            getLog().info("insertionMarker="+ (insertionMarker == null || insertionMarker.equals("") ? END_OF_WEBAPP : insertionMarker));
            getLog().info("keepSources=" + keepSources);
            getLog().info("mergeFragment=" + mergeFragment);  
            if (sourceVersion != null)
                getLog().info("sourceVersion="+sourceVersion);
            if (targetVersion != null)
                getLog().info("targetVersion="+targetVersion);
        }
        try
        {
            prepare();
            compile();
            cleanupSrcs();
            mergeWebXml();
        }
        catch (Exception e)
        {
            throw new MojoExecutionException("Failure processing jsps", e);
        }
    }
