    public static int runscript (File scriptFile) throws Exception
    {  
        //System.err.println("Running script:"+scriptFile.getAbsolutePath());
        try (FileInputStream fileStream = new FileInputStream(scriptFile))
        {
            Loader.loadClass("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection connection = DriverManager.getConnection(__dbURL, "", "");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            return ij.runScript(connection, fileStream, "UTF-8", out, "UTF-8");
        }
    }
