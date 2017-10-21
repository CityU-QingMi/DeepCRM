    public void testInheritance( String baseName, boolean fromRepo )
        throws Exception
    {
        Model parent = getModel( baseName + "-parent" );

        Model child = getModel( baseName + "-child" );

        if ( fromRepo )
        {
            // when model is read from repo, a stream is used, then pomFile == null
            // (has consequences in inheritance algorithm since getProjectDirectory() returns null)
            parent.setPomFile( null );
            child.setPomFile( null );
        }

        SimpleProblemCollector problems = new SimpleProblemCollector();

        assembler.assembleModelInheritance( child, parent, null, problems );

        // write baseName + "-actual"
        File actual = getTestFile( "target/test-classes/poms/inheritance/" + baseName
            + ( fromRepo ? "-build" : "-repo" ) + "-actual.xml" );
        writer.write( actual, null, child );

        // check with getPom( baseName + "-expected" )
        File expected = getPom( baseName + "-expected" );
        try ( Reader control = new InputStreamReader( new FileInputStream( expected ), StandardCharsets.UTF_8 );
              Reader test = new InputStreamReader( new FileInputStream( actual ), StandardCharsets.UTF_8 ) )
        {
            XMLUnit.setIgnoreComments( true );
            XMLUnit.setIgnoreWhitespace( true );
            XMLAssert.assertXMLEqual( control, test );
        }
    }    
