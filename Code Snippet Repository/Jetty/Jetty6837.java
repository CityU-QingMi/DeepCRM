    private void assertOutgoing(String text, String expectedHex) throws IOException
    {
        WebSocketPolicy policy = WebSocketPolicy.newClientPolicy();

        DeflateFrameExtension ext = new DeflateFrameExtension();
        ext.setBufferPool(bufferPool);
        ext.setPolicy(policy);

        ExtensionConfig config = ExtensionConfig.parse("deflate-frame");
        ext.setConfig(config);

        Generator generator = new Generator(policy, bufferPool, true);
        generator.configureFromExtensions(Collections.singletonList(ext));

        OutgoingNetworkBytesCapture capture = new OutgoingNetworkBytesCapture(generator);
        ext.setNextOutgoingFrames(capture);

        Frame frame = new TextFrame().setPayload(text);
        ext.outgoingFrame(frame, null, BatchMode.OFF);

        capture.assertBytes(0, expectedHex);
    }
