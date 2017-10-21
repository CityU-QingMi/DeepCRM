    @Override
    public void incomingFrame(Frame frame)
    {
        LOG.debug("incoming({})",frame);
        int count = parseCount.incrementAndGet();
        if ((count % 10) == 0)
        {
            LOG.info("Server parsed {} frames",count);
        }
        incomingFrames.incomingFrame(WebSocketFrame.copy(frame));

        if (frame.getOpCode() == OpCode.CLOSE)
        {
            CloseInfo close = new CloseInfo(frame);
            LOG.debug("Close frame: {}",close);
        }

        Type type = frame.getType();
        if (echoing.get() && (type.isData() || type.isContinuation()))
        {
            try
            {
                write(WebSocketFrame.copy(frame).setMasked(false));
            }
            catch (IOException e)
            {
                LOG.warn(e);
            }
        }
    }
