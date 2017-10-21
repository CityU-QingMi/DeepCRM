    @Override
    public void write(char[] chars, int off, int len) throws IOException
    {
        try
        {
            send(chars, off, len);
        }
        catch (Throwable x)
        {
            // Notify without holding locks.
            notifyFailure(x);
            throw x;
        }
    }
