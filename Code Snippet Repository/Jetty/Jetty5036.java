    public static String[] csvSplit(String s, int off,int len)
    {
        if (s==null)
            return null;
        if (off<0 || len<0 || off>s.length())
            throw new IllegalArgumentException();

        List<String> list = new ArrayList<>();
        csvSplit(list,s,off,len);
        return list.toArray(new String[list.size()]);
    }
