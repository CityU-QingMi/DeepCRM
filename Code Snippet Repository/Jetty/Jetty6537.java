    @Override
    public void onContinuationFrame(ByteBuffer buffer, boolean fin) throws IOException
    {
        if (activeMessage == null)
        {
            throw new IOException("Out of order Continuation frame encountered");
        }

        appendMessage(buffer,fin);
    }
