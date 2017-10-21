    public static List<String> getValues(String arg)
    {
        String v = getValue(arg);
        ArrayList<String> l = new ArrayList<>();
        for (String s : v.split(","))
        {
            if (s != null)
            {
                s = s.trim();
                if (s.length() > 0)
                {
                    l.add(s);
                }
            }
        }
        return l;
    }
