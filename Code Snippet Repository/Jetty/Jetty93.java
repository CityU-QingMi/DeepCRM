    public static String[] normalize (String[] list)
    {
        if (list == null)
            return null;       
        String[] normalList = new String[list.length];
        int i=0;
        for (String s : list)
            normalList[i++] = normalize(s);
        return normalList;
    }
