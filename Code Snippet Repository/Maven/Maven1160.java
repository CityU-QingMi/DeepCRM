    public void testGetModulePathAdjustment()
        throws IOException
    {
        Model moduleModel = new Model();

        MavenProject module = new MavenProject( moduleModel );
        module.setFile( new File( "module-dir/pom.xml" ) );

        Model parentModel = new Model();
        parentModel.addModule( "../module-dir" );

        MavenProject parent = new MavenProject( parentModel );
        parent.setFile( new File( "parent-dir/pom.xml" ) );

        String pathAdjustment = parent.getModulePathAdjustment( module );

        assertEquals( "..", pathAdjustment );
    }
