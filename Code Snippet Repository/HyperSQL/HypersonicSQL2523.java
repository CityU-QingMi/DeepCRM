    public String toString() {

        StringBuffer sb = new StringBuffer();

        sb.append('{');

        int         len = stringProps.size();
        Enumeration en  = stringProps.propertyNames();
        List list = Collections.list(en);
        Collections.sort(list);

        for (int i = 0; i < len; i++) {
            String key = (String) list.get(i);

            sb.append(key);
            sb.append('=');
            sb.append('"');
            sb.append(stringProps.get(key));
            sb.append('"');
            if (i + 1 < len) {
                sb.append(',');
                sb.append(' ');
            }
        }

        sb.append('}');

        return sb.toString();
    }
