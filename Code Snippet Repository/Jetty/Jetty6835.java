    @Test
    public void testGeneratedTwoFrames() throws IOException
    {
        WebSocketPolicy policy = WebSocketPolicy.newClientPolicy();

        DeflateFrameExtension ext = new DeflateFrameExtension();
        ext.setBufferPool(bufferPool);
        ext.setPolicy(policy);
        ext.setConfig(new ExtensionConfig(ext.getName()));

        Generator generator = new Generator(policy, bufferPool, true);
        generator.configureFromExtensions(Collections.singletonList(ext));

        OutgoingNetworkBytesCapture capture = new OutgoingNetworkBytesCapture(generator);
        ext.setNextOutgoingFrames(capture);

        ext.outgoingFrame(new TextFrame().setPayload("Hello"), null, BatchMode.OFF);
        ext.outgoingFrame(new TextFrame(), null, BatchMode.OFF);
        ext.outgoingFrame(new TextFrame().setPayload("There"), null, BatchMode.OFF);

        capture.assertBytes(0, "c107f248cdc9c90700");
    }
