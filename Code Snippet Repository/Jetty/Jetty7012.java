    public void copyLib(Class<?> clazz, String jarFileName) throws URISyntaxException, IOException
    {
        webinf = new File(contextDir,"WEB-INF");
        FS.ensureDirExists(webinf);
        File libDir = new File(webinf,"lib");
        FS.ensureDirExists(libDir);
        File jarFile = new File(libDir, jarFileName);
        
        URL codeSourceURL = clazz.getProtectionDomain().getCodeSource().getLocation();
        assertThat("Class CodeSource URL is file scheme", codeSourceURL.getProtocol(), is("file"));
    
        File sourceCodeSourceFile = new File(codeSourceURL.toURI());
        if (sourceCodeSourceFile.isDirectory())
        {
            LOG.info("Creating " + jarFile + " from " + sourceCodeSourceFile);
            JAR.create(sourceCodeSourceFile, jarFile);
        }
        else
        {
            LOG.info("Copying " + sourceCodeSourceFile + " to " + jarFile);
            IO.copy(sourceCodeSourceFile, jarFile);
        }
    }
