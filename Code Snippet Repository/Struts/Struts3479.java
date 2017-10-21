    public String[] getParameterValues(String name) {
        Enumeration enumeration = multi.getURLParameters(name);

        if (!enumeration.hasMoreElements()) {
            return null;
        }

        List<String> values = new ArrayList<String>();

        while (enumeration.hasMoreElements()) {
            values.add((String) enumeration.nextElement());
        }

        return values.toArray(new String[values.size()]);
    }
