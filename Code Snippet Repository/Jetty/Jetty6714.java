    @Test
    public void testFragmentedUnmaskedTextMessage()
    {
        WebSocketFrame text1 = new TextFrame().setPayload("Hel").setFin(false);
        WebSocketFrame text2 = new ContinuationFrame().setPayload("lo");

        ByteBuffer actual1 = UnitGenerator.generate(text1);
        ByteBuffer actual2 = UnitGenerator.generate(text2);

        ByteBuffer expected1 = ByteBuffer.allocate(5);

        expected1.put(new byte[]
                { (byte)0x01, (byte)0x03, (byte)0x48, (byte)0x65, (byte)0x6c });

        ByteBuffer expected2 = ByteBuffer.allocate(4);

        expected2.put(new byte[]
                { (byte)0x80, (byte)0x02, (byte)0x6c, (byte)0x6f });

        expected1.flip();
        expected2.flip();

        ByteBufferAssert.assertEquals("t1 buffers are not equal",expected1,actual1);
        ByteBufferAssert.assertEquals("t2 buffers are not equal",expected2,actual2);
    }
