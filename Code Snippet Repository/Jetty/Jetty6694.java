    @Test
    public void testNormal()
    {
        CloseInfo close = new CloseInfo(NORMAL);
        assertThat("close.code",close.getStatusCode(),is(NORMAL));
        assertThat("close.reason",close.getReason(),nullValue());

        CloseFrame frame = close.asFrame();
        assertThat("close frame op code",frame.getOpCode(),is(OpCode.CLOSE));
        assertThat("close frame payload length",frame.getPayloadLength(),is(2));
    }
