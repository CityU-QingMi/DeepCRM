    @Test
    public void testAnonymousClose()
    {
        CloseInfo close = new CloseInfo();
        assertThat("close.code",close.getStatusCode(),is(NO_CODE));
        assertThat("close.reason",close.getReason(),nullValue());

        CloseFrame frame = close.asFrame();
        assertThat("close frame op code",frame.getOpCode(),is(OpCode.CLOSE));
        // should result in no payload
        assertThat("close frame has payload",frame.hasPayload(),is(false));
        assertThat("close frame payload length",frame.getPayloadLength(),is(0));
    }
