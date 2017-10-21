    public String getWeakETag(String suffix)
    {
        try
        {
            StringBuilder b = new StringBuilder(32);
            b.append("W/\"");
            
            String name=getName();
            int length=name.length();
            long lhash=0;
            for (int i=0; i<length;i++)
                lhash=31*lhash+name.charAt(i);
            
            B64Code.encode(lastModified()^lhash,b);
            B64Code.encode(length()^lhash,b);
            b.append(suffix);
            b.append('"');
            return b.toString();
        } 
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
