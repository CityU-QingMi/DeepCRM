    public ThreadOutputMuxer( ProjectBuildList segmentChunks, PrintStream originalSystemOut )
    {
        projects = segmentChunks.iterator();
        for ( ProjectSegment segmentChunk : segmentChunks )
        {
            final ByteArrayOutputStream value = new ByteArrayOutputStream();
            streams.put( segmentChunk, value );
            printStreams.put( segmentChunk, new PrintStream( value ) );
        }
        setNext();
        this.originalSystemOUtStream = originalSystemOut;
        System.setOut( new ThreadBoundPrintStream( this.originalSystemOUtStream ) );
        printer = new ConsolePrinter( segmentChunks );
        new Thread( printer ).start();
        printer.waitUntilRunning( true );
    }
