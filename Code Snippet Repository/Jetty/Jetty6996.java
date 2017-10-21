    private void fastClose() throws Exception
    {
        try (IBlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setProtocols("fastclose");
            client.setTimeout(1,TimeUnit.SECONDS);
            try (StacklessLogging scope = new StacklessLogging(WebSocketSession.class))
            {
                client.connect();
                client.sendStandardRequest();
                client.expectUpgradeResponse();
                
                client.readFrames(1,1,TimeUnit.SECONDS);

                CloseInfo close = new CloseInfo(StatusCode.NORMAL,"Normal");
                assertThat("Close Status Code",close.getStatusCode(),is(StatusCode.NORMAL));

                // Notify server of close handshake
                client.write(close.asFrame()); // respond with close

                // ensure server socket got close event
                assertThat("Fast Close Latch",closeSocket.closeLatch.await(1,TimeUnit.SECONDS),is(true));
                assertThat("Fast Close.statusCode",closeSocket.closeStatusCode,is(StatusCode.NORMAL));
            }
        }
    }
