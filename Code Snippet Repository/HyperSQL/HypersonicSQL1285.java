    public void addUserVars(Map<String, String> newUserVars) {
        for (String val : newUserVars.values()) {
            if (val == null)
                throw new IllegalArgumentException(
                        "Null mapping values not allowed");
        }
        shared.userVars.putAll(newUserVars);
        List<String> strangeVars = new ArrayList<String>();
        for (String name : newUserVars.keySet())
            if (!name.equals("?") && !name.equals("#")
                    && !varPattern.matcher(name).matches())
                strangeVars.add(name);
        if (strangeVars.size() > 0)
            errprintln(SqltoolRB.varname_warning.getString(
                    strangeVars.toString()));
        sqlExpandMode = null;
    }
