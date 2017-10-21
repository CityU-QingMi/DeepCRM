    public void execute() throws BuildException {

        if (!isActive()) {
            return;
        }

        checkTargetDir();

        this.sourceDir = getProject().resolveFile("" + this.sourceDir);

        IResolver resolver = new AntResolver(getProject());
        String[]  files    = getFiles();

        log("Preprocessing " + files.length + " file(s)");

        try {
            Preprocessor.preprocessBatch(this.sourceDir, this.targetDir, files,
                    this.altExt, this.encoding, this.options, this.defines,
                    resolver);
        } catch (Exception ex) {
            ex.printStackTrace();

            throw new BuildException("Preprocessing failed: " + ex,
                    ex);
        }
    }
