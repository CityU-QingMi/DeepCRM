    public static String normalize (String name)
    {
        if (name==null)
            return null;

        if (name.startsWith("L") && name.endsWith(";"))
            name = name.substring(1, name.length()-1);

        if (name.endsWith(".class"))
            name = name.substring(0, name.length()-".class".length());

        return name.replace('/', '.');
    }
