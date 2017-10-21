    public void assertFileExistence( File dir, String filename, boolean shouldExist )
    {
        File file = new File( dir, filename );

        if ( shouldExist )
        {
            Assert.assertTrue( file.exists() );
        }
        else
        {
            Assert.assertFalse( file.exists() );
        }
    }
