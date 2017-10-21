    @Test
    @Ignore("")
    public void testUpgradeWithLargeFrame() throws Exception
    {
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        try
        {
            client.connect();

            // Create ByteBuffer representing the initial opening network packet from the client
            ByteBuffer initialPacket = ByteBuffer.allocate(100 * 1024);
            BufferUtil.clearToFill(initialPacket);

            // Add upgrade request to packet
            StringBuilder upgradeRequest = client.generateUpgradeRequest();
            ByteBuffer upgradeBuffer = BufferUtil.toBuffer(upgradeRequest.toString(),StandardCharsets.UTF_8);
            initialPacket.put(upgradeBuffer);

            // Add text frames
            Generator generator = new Generator(WebSocketPolicy.newClientPolicy(),
                    new LeakTrackingByteBufferPool(new MappedByteBufferPool.Tagged()));

            byte bigMsgBytes[] = new byte[64*1024];
            Arrays.fill(bigMsgBytes,(byte)'x');
            String bigMsg = new String(bigMsgBytes, StandardCharsets.UTF_8);
            
            // Need to set frame mask (as these are client frames)
            byte mask[] = new byte[] { 0x11, 0x22, 0x33, 0x44 };
            TextFrame frame = new TextFrame().setPayload(bigMsg);
            frame.setMask(mask);
            generator.generateWholeFrame(frame,initialPacket);

            // Write packet to network
            BufferUtil.flipToFlush(initialPacket,0);
            client.writeRaw(initialPacket);

            // Expect upgrade
            client.expectUpgradeResponse();

            // Read frames (hopefully text frames)
            EventQueue<WebSocketFrame> frames = client.readFrames(1,1,TimeUnit.SECONDS);

            WebSocketFrame tf = frames.poll();
            Assert.assertThat("Text Frame/msg1",tf.getPayloadAsUTF8(),is(bigMsg));
        }
        finally
        {
            client.close();
        }
    }
