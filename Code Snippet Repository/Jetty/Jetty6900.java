    @Override
    public void write(WebSocketFrame frame) throws IOException
    {
        if (!ioState.isOpen())
        {
            LOG.debug("IO Not Open / Not Writing: {}",frame);
            return;
        }
        LOG.debug("write(Frame->{}) to {}",frame,outgoing);
        if (LOG.isDebugEnabled())
        {
            frame.setMask(new byte[] { 0x00, 0x00, 0x00, 0x00 });
        }
        else
        {
            frame.setMask(clientmask);
        }
        extensionStack.outgoingFrame(frame,null,BatchMode.OFF);
    }
