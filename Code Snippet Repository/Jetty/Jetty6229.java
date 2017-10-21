    @SuppressWarnings("")
    @Test
    public void testAnnotatedRuntimeOnOpen() throws Exception
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        server.addBean(container); // allow to shutdown with server
        AnnotatedRuntimeOnOpen socket = new AnnotatedRuntimeOnOpen();

        try (StacklessLogging logging = new StacklessLogging(AnnotatedRuntimeOnOpen.class, WebSocketSession.class))
        {
            // expecting IOException during onOpen
            expectedException.expect(IOException.class);
            expectedException.expectCause(instanceOf(RuntimeException.class));
            container.connectToServer(socket, serverUri);
            expectedException.reportMissingExceptionWithMessage("Should have failed .connectToServer()");
            
            assertThat("Close should have occurred",socket.closeLatch.await(1,TimeUnit.SECONDS),is(true));

            Throwable cause = socket.errors.pop();
            assertThat("Error",cause,instanceOf(ArrayIndexOutOfBoundsException.class));
        }
    }
