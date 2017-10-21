    public void testMultiThreaded()
        throws Exception
    {
        ProjectBuildList projectBuildList = getProjectBuildList();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream systemOut = new PrintStream( byteArrayOutputStream );
        final ThreadOutputMuxer threadOutputMuxer = new ThreadOutputMuxer( projectBuildList, systemOut );

        final List<String> stringList =
            Arrays.asList( "Thinkin", "of", "a", "master", "plan", "Cuz", "ain’t", "nuthin", "but", "sweat", "inside",
                           "my", "hand" );
        Iterator<String> lyrics = stringList.iterator();

        ExecutorService executor = Executors.newFixedThreadPool( 10 );
        CompletionService<ProjectSegment> service = new ExecutorCompletionService<>( executor );

        List<Future<ProjectSegment>> futures = new ArrayList<>();
        for ( ProjectSegment projectBuild : projectBuildList )
        {
            final Future<ProjectSegment> buildFuture =
                service.submit( new Outputter( threadOutputMuxer, projectBuild, lyrics.next() ) );
            futures.add( buildFuture );
        }

        for ( Future<ProjectSegment> future : futures )
        {
            future.get();
        }
        int expectedLength = 0;
        for ( int i = 0; i < projectBuildList.size(); i++ )
        {
            expectedLength += stringList.get( i ).length();
        }

        threadOutputMuxer.close();
        final byte[] bytes = byteArrayOutputStream.toByteArray();
        String result = new String( bytes );
        assertEquals( result, expectedLength, bytes.length );


    }
