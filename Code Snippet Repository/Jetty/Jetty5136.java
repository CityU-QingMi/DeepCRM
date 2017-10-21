    protected static String condensePackageString(String classname)
    {
        if(classname == null || classname.isEmpty())
        {
            return "";
        }
        // strip non-allowed character
        String allowed = classname.replaceAll("[^\\w.]", "");
        int len = allowed.length();
        // find end of classname (strip empty sections. eg: "org.Foo.")
        while(allowed.charAt(--len) == '.');
        String parts[] = allowed.substring(0,len+1).split("\\.");
        StringBuilder dense = new StringBuilder();
        for (int i = 0; i < (parts.length - 1); i++)
        {
            String part = parts[i].trim();
            if(!part.isEmpty())
            {
                dense.append(part.charAt(0));
            }
        }
        if (dense.length() > 0)
        {
            dense.append('.');
        }
        dense.append(parts[parts.length - 1]);
        return dense.toString();
    }
