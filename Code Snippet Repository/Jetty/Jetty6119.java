    @Override
    public boolean willDecode(String s)
    {
        if (s == null)
        {
            return false;
        }

        try
        {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
