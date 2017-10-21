    public void assertFileContents( File dir, String filename, String contentsTest, String encoding )
        throws IOException
    {
        assertFileExistence( dir, filename, true );

        File file = new File( dir, filename );

        String contents = FileUtils.fileRead( file, encoding );

        Assert.assertEquals( contentsTest, contents );
    }
