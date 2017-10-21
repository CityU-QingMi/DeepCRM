    public String[] getStringArrayProperty(String name, String[] def) {
        String vals = getStringProperty(name);
        if (vals == null) {
            return def;
        }

        StringTokenizer stok = new StringTokenizer(vals, ",");
        ArrayList<String> strs = new ArrayList<String>();
        try {
            while (stok.hasMoreTokens()) {
                strs.add(stok.nextToken().trim());
            }
            
            return (String[])strs.toArray(new String[strs.size()]);
        } catch (Exception e) {
            return def;
        }
    }
