    @Test(timeout=2000)
    public void testEcho() throws Exception
    {
        int messageCount = testcase.getMessageCount();
        EchoClientSocket socket = new EchoClientSocket(messageCount);
        URI toUri = serverUri.resolve(testcase.path.substring(1));

        try
        {
            // Connect
            client.connectToServer(socket,toUri);
            socket.waitForConnected(2,TimeUnit.SECONDS);

            // Send Messages
            for (Object msg : testcase.messages)
            {
                if (msg instanceof PartialText)
                {
                    PartialText pt = (PartialText)msg;
                    socket.sendPartialText(pt.part,pt.fin);
                }
                else if (msg instanceof PartialBinary)
                {
                    PartialBinary pb = (PartialBinary)msg;
                    socket.sendPartialBinary(pb.part,pb.fin);
                }
                else
                {
                    socket.sendObject(msg);
                }
            }

            // Collect Responses
            socket.awaitAllEvents(1,TimeUnit.SECONDS);
            EventQueue<String> received = socket.eventQueue;

            // Validate Responses
            for (String expected : testcase.expectedStrings)
            {
                Assert.assertThat("Received Echo Responses",received,contains(expected));
            }
        }
        finally
        {
            // Close
            socket.close();
        }
    }
