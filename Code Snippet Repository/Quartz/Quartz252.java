    private String[] split(String string, String delim) {
        ArrayList<String> result = new ArrayList<String>();
        
        StringTokenizer stringTokenizer = new StringTokenizer(string, delim);
        while (stringTokenizer.hasMoreTokens()) {
            result.add(stringTokenizer.nextToken());
        }
        
        return (String[])result.toArray(new String[result.size()]);
    }
