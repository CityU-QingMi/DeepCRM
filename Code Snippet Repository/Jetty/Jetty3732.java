    private File setupTestDirectory() throws IOException
    {
        File tmpDir = new File( System.getProperty( "basedir",".") + "/target/tmp/ContextHandlerTest" );
        tmpDir=tmpDir.getCanonicalFile();
        if (!tmpDir.exists())
            Assert.assertTrue(tmpDir.mkdirs());
        File tmp = File.createTempFile("cht",null, tmpDir );
        Assert.assertTrue(tmp.delete());
        Assert.assertTrue(tmp.mkdir());
        tmp.deleteOnExit();
        File root = new File(tmp,getClass().getName());
        Assert.assertTrue(root.mkdir());

        File webInf = new File(root,"WEB-INF");
        Assert.assertTrue(webInf.mkdir());

        Assert.assertTrue(new File(webInf, "jsp").mkdir());
        Assert.assertTrue(new File(webInf, "web.xml").createNewFile());

        return root;
    }
