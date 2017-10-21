    public void append(String s,int offset,int length)
    {
        try
        {
            checkCharAppend();
            _appendable.append(s,offset,offset+length); 
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
