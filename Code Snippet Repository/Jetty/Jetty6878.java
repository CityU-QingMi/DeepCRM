    @Test(timeout=10000)
    public void testAppendEmptyPayloadRead() throws IOException
    {
        try (MessageInputStream stream = new MessageInputStream())
        {
            // Append parts of message
            ByteBuffer msg1 = BufferUtil.toBuffer("Hello ",StandardCharsets.UTF_8);
            ByteBuffer msg2 = ByteBuffer.allocate(0); // what is being tested
            ByteBuffer msg3 = BufferUtil.toBuffer("World",StandardCharsets.UTF_8);
            
            stream.appendFrame(msg1,false);
            stream.appendFrame(msg2,false);
            stream.appendFrame(msg3,true);

            // Read entire message it from the stream.
            byte buf[] = new byte[32];
            int len = stream.read(buf);
            String message = new String(buf,0,len,StandardCharsets.UTF_8);

            // Test it
            Assert.assertThat("Message",message,is("Hello World"));
        }
    }
