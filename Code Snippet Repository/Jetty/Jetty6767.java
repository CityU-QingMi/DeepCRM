    @Test
    public void testGenerate125OctetPingCase2_4()
    {
        byte[] bytes = new byte[125];

        for ( int i = 0 ; i < bytes.length ; ++i )
        {
            bytes[i] = Integer.valueOf(Integer.toOctalString(i)).byteValue();
        }

        WebSocketFrame pingFrame = new PingFrame().setPayload(bytes);

        ByteBuffer actual = UnitGenerator.generate(pingFrame);

        ByteBuffer expected = ByteBuffer.allocate(bytes.length + 32);

        expected.put(new byte[]
                { (byte)0x89 });

        byte b = 0x00; // no masking
        b |= bytes.length & 0x7F;
        expected.put(b);
        expected.put(bytes);

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
