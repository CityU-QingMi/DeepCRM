    private String[] split(String str, String splitStr) // Same as String.split(.) in JDK 1.4
    {
        LinkedList<String> l = new LinkedList<String>();
    
        StringTokenizer strTok = new StringTokenizer(str, splitStr);
        while(strTok.hasMoreTokens()) {
            String tok = strTok.nextToken();
            l.add(tok);
        }
    
        return (String[])l.toArray(new String[l.size()]);
    }
