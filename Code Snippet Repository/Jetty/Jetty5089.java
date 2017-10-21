    public void append(String s)
    {
        try
        {
            checkCharAppend();
            _appendable.append(s); 
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
