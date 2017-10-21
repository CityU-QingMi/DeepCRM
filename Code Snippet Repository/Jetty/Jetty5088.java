    public void append(char c)
    {
        try
        {
            checkCharAppend();
            _appendable.append(c); 
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
