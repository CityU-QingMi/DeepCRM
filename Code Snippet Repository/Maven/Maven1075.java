    public void testSingleThreaded()
        throws Exception
    {
        ProjectBuildList src = getProjectBuildList();
        ProjectBuildList projectBuildList =
            new ProjectBuildList( Arrays.asList( src.get( 0 ), src.get( 1 ), src.get( 2 ) ) );

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream systemOut = new PrintStream( byteArrayOutputStream );
        ThreadOutputMuxer threadOutputMuxer = new ThreadOutputMuxer( projectBuildList, systemOut );

        threadOutputMuxer.associateThreadWithProjectSegment( projectBuildList.get( 0 ) );
        System.out.print( paid );  // No, this does not print to system.out. It's part of the test
        assertEquals( paid.length(), byteArrayOutputStream.size() );
        threadOutputMuxer.associateThreadWithProjectSegment( projectBuildList.get( 1 ) );
        System.out.print( in );  // No, this does not print to system.out. It's part of the test
        assertEquals( paid.length(), byteArrayOutputStream.size() );
        threadOutputMuxer.associateThreadWithProjectSegment( projectBuildList.get( 2 ) );
        System.out.print( full ); // No, this does not print to system.out. It's part of the test
        assertEquals( paid.length(), byteArrayOutputStream.size() );

        threadOutputMuxer.setThisModuleComplete( projectBuildList.get( 0 ) );
        threadOutputMuxer.setThisModuleComplete( projectBuildList.get( 1 ) );
        threadOutputMuxer.setThisModuleComplete( projectBuildList.get( 2 ) );
        threadOutputMuxer.close();
        assertEquals( ( paid + in + full ).length(), byteArrayOutputStream.size() );
    }
