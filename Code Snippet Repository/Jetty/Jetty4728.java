    public void appendBoolean(Appendable buffer, Boolean b)
    {
        try
        {
            if (b == null)
            {
                appendNull(buffer);
                return;
            }
            buffer.append(b?"true":"false");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
