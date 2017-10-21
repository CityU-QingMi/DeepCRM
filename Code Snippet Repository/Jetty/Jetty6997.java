    private void fastFail() throws Exception
    {
        try (IBlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setProtocols("fastfail");
            client.setTimeout(1,TimeUnit.SECONDS);
            try (StacklessLogging scope = new StacklessLogging(WebSocketSession.class))
            {
                client.connect();
                client.sendStandardRequest();
                client.expectUpgradeResponse();
                
                // client.readFrames(1,2,TimeUnit.SECONDS);

                CloseInfo close = new CloseInfo(StatusCode.NORMAL,"Normal");
                client.write(close.asFrame()); // respond with close

                // ensure server socket got close event
                assertThat("Fast Fail Latch",closeSocket.closeLatch.await(1,TimeUnit.SECONDS),is(true));
                assertThat("Fast Fail.statusCode",closeSocket.closeStatusCode,is(StatusCode.SERVER_ERROR));
                assertThat("Fast Fail.errors",closeSocket.errors.size(),is(1));
            }
        }
    }
