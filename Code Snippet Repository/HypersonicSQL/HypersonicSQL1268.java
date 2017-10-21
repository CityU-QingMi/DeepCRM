    private void dump(String varName,
                      File dumpFile) throws IOException, BadSpecial {
        String val = shared.userVars.get(varName);

        if (val == null)
            throw new BadSpecial(SqltoolRB.plvar_undefined.getString(varName));

        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream(dumpFile), (shared.encoding == null)
                ? DEFAULT_FILE_ENCODING : shared.encoding);

        try {
            osw.write(val);

            if (val.length() > 0) {
                char lastChar = val.charAt(val.length() - 1);

                if (lastChar != '\n' && lastChar != '\r') osw.write(LS);
            }

            osw.flush();
        } finally {
            try {
                osw.close();
            } catch (IOException ioe) {
                // Intentionally empty
            } finally {
                osw = null;  // Encourage GC of buffers
            }
        }

        // Since opened in overwrite mode, since we didn't exception out,
        // we can be confident that we wrote all the bytest in the file.
        stdprintln(SqltoolRB.file_wrotechars.getString(
                Long.toString(dumpFile.length()), dumpFile.toString()));
    }
