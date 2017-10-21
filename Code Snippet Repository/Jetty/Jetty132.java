    private void copyClass(Class<?> clazz, File basedir) throws IOException
    {
        String classname = clazz.getName().replace('.',File.separatorChar) + ".class";
        URL url = this.getClass().getResource('/'+classname);
        Assert.assertThat("URL for: " + classname,url,notNullValue());

        String classpath = classname.substring(0,classname.lastIndexOf(File.separatorChar));
        FS.ensureDirExists(new File(basedir,classpath));

        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = url.openStream();
            out = new FileOutputStream(new File(basedir,classname));
            IO.copy(in,out);
        }
        finally
        {
            IO.close(out);
            IO.close(in);
        }
    }
