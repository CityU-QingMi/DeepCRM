    @Override
    public boolean willDecode(String s)
    {
        if (s == null)
        {
            return false;
        }
        if (s.length() == 1)
        {
            return true;
        }
        // can only parse 1 character
        return false;
    }
