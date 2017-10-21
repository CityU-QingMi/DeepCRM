    @Test(expected = ProtocolException.class)
    public void testCase7_3_6GenerateCloseWithInvalidStatusReason()
    {
        StringBuilder message = new StringBuilder();
        for ( int i = 0 ; i < 124 ; ++i )
        {
            message.append("*");
        }

        byte[] messageBytes = message.toString().getBytes();

        CloseFrame closeFrame = new CloseFrame();

        ByteBuffer bb = ByteBuffer.allocate(CloseFrame.MAX_CONTROL_PAYLOAD + 1); // 126 which is too big for control

        bb.putChar((char)1000);
        bb.put(messageBytes);

        BufferUtil.flipToFlush(bb,0);

        closeFrame.setPayload(bb);

        UnitGenerator.generate(closeFrame);
    }
