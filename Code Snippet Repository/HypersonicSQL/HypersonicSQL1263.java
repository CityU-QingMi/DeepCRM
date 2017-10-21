    private SqlFile(SqlFile parentSqlFile, Reader reader,
            String inputStreamLabel, File baseDir) {
        this(reader, inputStreamLabel, baseDir);
        try {
            recursed = Recursion.FILE;
            shared = parentSqlFile.shared;
            // shared.userVars.put("?", "");  Don't destroy this useful value!
            interactive = false;
            continueOnError = parentSqlFile.continueOnError;
            // Nested input is non-interactive because it just can't work to
            // have user append to edit buffer, and displaying prompts would
            // be misleading and inappropriate; yet we will inherit the current
            // continueOnError behavior.
        } catch (RuntimeException re) {
            closeReader();
            throw re;
        }
    }
