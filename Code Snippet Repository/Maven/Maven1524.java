    public void testFlatTrickyUrls()
        throws Exception
    {
        // parent references child with artifactId (which is not directory name)
        // then relative path calculation will fail during build from disk but success when calculated from repo
        try
        {
            // build from disk expected to fail
            testInheritance( "tricky-flat-artifactId-urls", false );
            //fail( "should have failed since module reference == artifactId != directory name" );
        }
        catch ( AssertionFailedError afe )
        {
            // expected failure: wrong relative path calculation
            assertTrue( afe.getMessage(),
                        afe.getMessage().contains( "http://www.apache.org/path/to/parent/child-artifact-id/" ) );
        }
        // but ok from repo: local disk is ignored
        testInheritance( "tricky-flat-artifactId-urls", true );

        // parent references child with directory name (which is not artifact id)
        // then relative path calculation will success during build from disk but failwhen calculated from repo
        testInheritance( "tricky-flat-directory-urls", false );
        try
        {
            testInheritance( "tricky-flat-directory-urls", true );
            fail( "should have failed since module reference == directory name != artifactId" );
        }
        catch ( AssertionFailedError afe )
        {
            // expected failure
            assertTrue( afe.getMessage(),
                        afe.getMessage().contains( "http://www.apache.org/path/to/parent/child-artifact-id/" ) );
        }
    }
