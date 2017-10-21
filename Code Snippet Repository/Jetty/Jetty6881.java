    @Test
    public void testMultipleWrites() throws Exception
    {
        try (MessageOutputStream stream = new MessageOutputStream(session))
        {
            stream.write("Hello".getBytes("UTF-8"));
            stream.write(" ".getBytes("UTF-8"));
            stream.write("World".getBytes("UTF-8"));
        }

        Assert.assertThat("Socket.messageQueue.size",socket.messageQueue.size(),is(1));
        String msg = socket.messageQueue.poll();
        Assert.assertThat("Message",msg,allOf(containsString("byte[11]"),containsString("Hello World")));
    }
