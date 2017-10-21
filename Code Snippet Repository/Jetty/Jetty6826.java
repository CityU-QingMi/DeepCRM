        public void assertHasFrames(Frame... expectedFrames)
        {
            int expectedCount = expectedFrames.length;
            capture.assertFrameCount(expectedCount);

            for (int i = 0; i < expectedCount; i++)
            {
                WebSocketFrame actual = capture.getFrames().poll();

                String prefix = String.format("frame[%d]",i);
                Assert.assertThat(prefix + ".opcode",actual.getOpCode(),is(expectedFrames[i].getOpCode()));
                Assert.assertThat(prefix + ".fin",actual.isFin(),is(expectedFrames[i].isFin()));
                Assert.assertThat(prefix + ".rsv1",actual.isRsv1(),is(false));
                Assert.assertThat(prefix + ".rsv2",actual.isRsv2(),is(false));
                Assert.assertThat(prefix + ".rsv3",actual.isRsv3(),is(false));

                ByteBuffer expected = expectedFrames[i].getPayload().slice();
                Assert.assertThat(prefix + ".payloadLength",actual.getPayloadLength(),is(expected.remaining()));
                ByteBufferAssert.assertEquals(prefix + ".payload",expected,actual.getPayload().slice());
            }
        }
