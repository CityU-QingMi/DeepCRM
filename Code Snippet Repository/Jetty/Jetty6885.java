    @Test
    public void testMultipleWrites() throws Exception
    {
        try (MessageWriter stream = new MessageWriter(session))
        {
            stream.write("Hello");
            stream.write(" ");
            stream.write("World");
        }

        Assert.assertThat("Socket.messageQueue.size",socket.messageQueue.size(),is(1));
        String msg = socket.messageQueue.poll();
        Assert.assertThat("Message",msg,is("Hello World"));
    }
