    public void appendNull(Appendable buffer)
    {
        try
        {
            buffer.append("null");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
