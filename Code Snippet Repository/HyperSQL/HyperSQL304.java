    private void load(String varName, File asciiFile, String cs)
            throws IOException {
        String string = streamToString(new FileInputStream(asciiFile), cs);
        // The streamToString() method ensures that the Stream gets closed
        shared.userVars.put(varName, string);
        if (!varPattern.matcher(varName).matches())
            errprintln(SqltoolRB.varname_warning.getString(varName));
        updateUserSettings();
        sqlExpandMode = null;
    }
