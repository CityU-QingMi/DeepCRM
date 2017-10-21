    public void testJdk7ipv6()
    {
        ConnectException connEx = new ConnectException( "Connection refused: connect" );
        IOException ioEx = new IOException( "Unable to establish loopback connection" );
        ioEx.initCause( connEx );
        MojoExecutionException mojoEx =
            new MojoExecutionException( "Error executing Jetty: Unable to establish loopback connection", ioEx );

        ExceptionHandler exceptionHandler = new DefaultExceptionHandler();
        ExceptionSummary exceptionSummary = exceptionHandler.handleException( mojoEx );

        String expectedReference = "http://cwiki.apache.org/confluence/display/MAVEN/ConnectException";
        assertEquals( expectedReference, exceptionSummary.getReference() );

    }
