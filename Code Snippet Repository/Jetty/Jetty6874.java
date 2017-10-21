    @Test(timeout=10000)
    public void testBasicAppendRead() throws IOException
    {
        try (MessageInputStream stream = new MessageInputStream())
        {
            // Append a single message (simple, short)
            ByteBuffer payload = BufferUtil.toBuffer("Hello World",StandardCharsets.UTF_8);
            boolean fin = true;
            stream.appendFrame(payload,fin);

            // Read entire message it from the stream.
            byte buf[] = new byte[32];
            int len = stream.read(buf);
            String message = new String(buf,0,len,StandardCharsets.UTF_8);

            // Test it
            Assert.assertThat("Message",message,is("Hello World"));
        }
    }
