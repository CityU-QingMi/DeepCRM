    public static String trimClassName(String name)
    {
        int idx = name.lastIndexOf('.');
        name = name.substring(idx + 1);
        idx = name.lastIndexOf('$');
        if (idx >= 0)
        {
            name = name.substring(idx + 1);
        }
        return name;
    }
