    private void setClientMask(WebSocketFrame f)
    {
        if (LOG.isDebugEnabled())
        {
            f.setMask(new byte[]
            { 0x00, 0x00, 0x00, 0x00 });
        }
        else
        {
            f.setMask(MASK); // make sure we have mask set
        }
    }
