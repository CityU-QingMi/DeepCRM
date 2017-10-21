    public static boolean match (String name, String... names)
    {
        if (name == null || names == null)
            return false;
        boolean matched = false;
        for (int i=0; i< names.length && !matched; i++)
            if (name.equals(names[i]))
                matched = true;
        return matched;
    }
