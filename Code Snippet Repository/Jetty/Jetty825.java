    public void copyWebapp(String srcName, String destName) throws IOException
    {
        System.err.printf("Copying Webapp: %s -> %s%n",srcName,destName);
        File srcDir = MavenTestingUtils.getTestResourceDir("webapps");
        File destDir = new File(_jettyHome,"webapps");

        File srcFile = new File(srcDir,srcName);
        File destFile = new File(destDir,destName);

        copyFile("Webapp",srcFile,destFile);
    }
