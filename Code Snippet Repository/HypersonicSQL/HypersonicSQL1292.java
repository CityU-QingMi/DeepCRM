    synchronized public void execute() throws SqlToolError, SQLException {
        if (reader == null)
            throw new IllegalStateException("Can't call execute() "
                    + "more than once for a single SqlFile instance");
        updateUserSettings();

        try {
            scanner = new SqlFileScanner(reader);
            scanner.setStdPrintStream(shared.psStd);
            scanner.setRawLeadinPrompt(SqltoolRB.raw_leadin.getString());
            if (interactive) {
                stdprintln(SqltoolRB.SqlFile_banner.getString(revnum));
                scanner.setRawPrompt(rawPrompt);
                scanner.setSqlPrompt(contPrompt);
                scanner.setSqltoolPrompt(primaryPrompt);
                scanner.setInteractive(true);
                if (shared.jdbcConn == null)
                    stdprintln(SqltoolRB.suggest_j.getString());
                stdprint(primaryPrompt);
            }
            scanpass(scanner);
        } finally {
            try {
                closeQueryOutputStream();
                if (autoClose) closeReader();
            } finally {
                reader = null; // Encourage GC of buffers
            }
        }
    }
