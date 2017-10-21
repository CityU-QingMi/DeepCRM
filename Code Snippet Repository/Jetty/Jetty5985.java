    @Override
    public void cloneConfigure(WebAppContext template, WebAppContext context) throws Exception
    {
        File tmpDir=File.createTempFile(WebInfConfiguration.getCanonicalNameForWebAppTmpDir(context),"",template.getTempDirectory().getParentFile());
        if (tmpDir.exists())
        {
            IO.delete(tmpDir);
        }
        tmpDir.mkdir();
        tmpDir.deleteOnExit();
        context.setTempDirectory(tmpDir);
    }
