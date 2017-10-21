    private File getWebXmlFile ()
    throws IOException
    {
        File file = null;
        File baseDir = project.getBasedir().getCanonicalFile();
        File defaultWebAppSrcDir = new File (baseDir, "src/main/webapp").getCanonicalFile();
        File webAppSrcDir = new File (webAppSourceDirectory).getCanonicalFile();
        File defaultWebXml = new File (defaultWebAppSrcDir, "web.xml").getCanonicalFile();
        
        //If the web.xml has been changed from the default, try that
        File webXmlFile = new File (webXml).getCanonicalFile();
        if (webXmlFile.compareTo(defaultWebXml) != 0)
        {
            file = new File (webXml);
            return file;
        }
        
        //If the web app src directory has not been changed from the default, use whatever
        //is set for the web.xml location
        file = new File (webAppSrcDir, "web.xml");
        return file;
    }
