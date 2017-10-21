    public SqlFile(Reader reader, String inputStreamLabel,
            PrintStream psStd, String encoding, boolean interactive,
            File baseDir) throws IOException {
        this(reader, inputStreamLabel, baseDir);
        try {
            shared = new SharedFields(psStd);
            shared.userVars.put(
                    "*START_TIME", (new java.util.Date()).toString());
            shared.userVars.put("*REVISION", revnum);
            shared.userVars.put("?", "");
            shared.userVars.put("#", "0");
            setEncoding(encoding);
            this.interactive = interactive;
            continueOnError = this.interactive;

            if (interactive) {
                history = new TokenList();
                maxHistoryLength = DEFAULT_HISTORY_SIZE;
            }
            // Updates local vars basd on * shared.userVars
            // even when (like now) these are all defaults.
        } catch (IOException ioe) {
            closeReader();
            throw ioe;
        } catch (RuntimeException re) {
            closeReader();
            throw re;
        }
    }
