    @Test
    public void testFromFrame()
    {
        ByteBuffer payload = asByteBuffer(NORMAL,null);
        assertThat("payload length", payload.remaining(), is(2));
        CloseFrame frame = new CloseFrame();
        frame.setPayload(payload);
        
        // create from frame
        CloseInfo close = new CloseInfo(frame);
        assertThat("close.code",close.getStatusCode(),is(NORMAL));
        assertThat("close.reason",close.getReason(),nullValue());

        // and back again
        frame = close.asFrame();
        assertThat("close frame op code",frame.getOpCode(),is(OpCode.CLOSE));
        assertThat("close frame payload length",frame.getPayloadLength(),is(2));
    }
