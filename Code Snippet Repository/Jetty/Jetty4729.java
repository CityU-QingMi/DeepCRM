    public void appendNumber(Appendable buffer, Number number)
    {
        try
        {
            if (number == null)
            {
                appendNull(buffer);
                return;
            }
            buffer.append(String.valueOf(number));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
