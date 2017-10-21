    public void testModulePathNotArtifactId()
        throws Exception
    {
        Model parent = getModel( "module-path-not-artifactId-parent" );

        Model child = getModel( "module-path-not-artifactId-child" );

        SimpleProblemCollector problems = new SimpleProblemCollector();

        assembler.assembleModelInheritance( child, parent, null, problems );

        File actual = getTestFile( "target/test-classes/poms/inheritance/module-path-not-artifactId-actual.xml" );

        writer.write( actual, null, child );

        // check with getPom( "module-path-not-artifactId-effective" )
        File expected = getPom( "module-path-not-artifactId-expected" );
        try ( Reader control = new InputStreamReader( new FileInputStream( expected ), StandardCharsets.UTF_8 );
                        Reader test = new InputStreamReader( new FileInputStream( actual ), StandardCharsets.UTF_8 ) )
        {
            XMLUnit.setIgnoreComments( true );
            XMLUnit.setIgnoreWhitespace( true );
            XMLAssert.assertXMLEqual( control, test );
        }
    }
