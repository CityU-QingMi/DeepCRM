    public void copyClass(Class<?> clazz) throws Exception
    {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        String endpointPath = clazz.getName().replace('.','/') + ".class";
        URL classUrl = cl.getResource(endpointPath);
        Assert.assertThat("Class URL for: " + clazz,classUrl,notNullValue());
        File destFile = new File(classesDir,OS.separators(endpointPath));
        FS.ensureDirExists(destFile.getParentFile());
        File srcFile = new File(classUrl.toURI());
        IO.copy(srcFile,destFile);
    }
