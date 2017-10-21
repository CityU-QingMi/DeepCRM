    @Override
    public void write(int b) throws IOException
    {
        try
        {
            send(new byte[]{(byte)b}, 0, 1);
        }
        catch (Throwable x)
        {
            // Notify without holding locks.
            notifyFailure(x);
            throw x;
        }
    }
