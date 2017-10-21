    protected void appendMessage(ByteBuffer buffer, boolean fin) throws IOException
    {
        activeMessage.appendFrame(buffer,fin);

        if (fin)
        {
            activeMessage.messageComplete();
            activeMessage = null;
        }
    }
