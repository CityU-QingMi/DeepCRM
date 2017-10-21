    public static String printable(String name)
    {
        if (name==null)
            return null;
        StringBuilder buf = new StringBuilder(name.length());
        for (int i=0;i<name.length();i++)
        {
            char c=name.charAt(i);
            if (!Character.isISOControl(c))
                buf.append(c);
        }
        return buf.toString();
    }
