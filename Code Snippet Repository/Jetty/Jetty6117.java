    @Override
    public boolean willDecode(String s)
    {
        if (s == null)
        {
            return false;
        }
        try
        {
            Float val = Float.parseFloat(s);
            return (!val.isNaN());
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
