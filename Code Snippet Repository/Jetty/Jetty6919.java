    public void echoMessage(int expectedFrames, int timeoutDuration, TimeUnit timeoutUnit) throws IOException, TimeoutException
    {
        LOG.debug("Echo Frames [expecting {}]",expectedFrames);
        IncomingFramesCapture cap = readFrames(expectedFrames,timeoutDuration,timeoutUnit);
        // now echo them back.
        for (Frame frame : cap.getFrames())
        {
            write(WebSocketFrame.copy(frame).setMasked(false));
        }
    }
