    public void expect(List<WebSocketFrame> expect, int duration, TimeUnit unit) throws Exception
    {
        int expectedCount = expect.size();
        LOG.debug("expect() {} frame(s)",expect.size());

        // Read frames
        EventQueue<WebSocketFrame> frames = client.readFrames(expect.size(),duration,unit);
        
        String prefix = "";
        for (int i = 0; i < expectedCount; i++)
        {
            WebSocketFrame expected = expect.get(i);
            WebSocketFrame actual = frames.poll();

            prefix = "Frame[" + i + "]";

            LOG.debug("{} {}",prefix,actual);

            Assert.assertThat(prefix + ".opcode",OpCode.name(actual.getOpCode()),is(OpCode.name(expected.getOpCode())));
            prefix += "/" + actual.getOpCode();
            if (expected.getOpCode() == OpCode.CLOSE)
            {
                CloseInfo expectedClose = new CloseInfo(expected);
                CloseInfo actualClose = new CloseInfo(actual);
                Assert.assertThat(prefix + ".statusCode",actualClose.getStatusCode(),is(expectedClose.getStatusCode()));
            }
            else
            {
                Assert.assertThat(prefix + ".payloadLength",actual.getPayloadLength(),is(expected.getPayloadLength()));
                ByteBufferAssert.assertEquals(prefix + ".payload",expected.getPayload(),actual.getPayload());
            }
        }
    }
