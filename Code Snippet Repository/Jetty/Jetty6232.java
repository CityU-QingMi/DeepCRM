    @Override
    public boolean willDecode(String s)
    {
        if (s == null)
        {
            return false;
        }

        Pattern pat = Pattern.compile("([^|]*)|([^|]*)");
        Matcher mat = pat.matcher(s);
        return (mat.find());
    }
