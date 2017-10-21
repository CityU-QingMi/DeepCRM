    private String toCSV (List<String> strings)
    {
        if (strings == null)
            return "";
        StringBuffer strbuff = new StringBuffer();
        Iterator<String> itor = strings.iterator();
        while (itor.hasNext())
        {
            strbuff.append(itor.next());
            if (itor.hasNext())
                strbuff.append(",");
        }
        return strbuff.toString();
    }
