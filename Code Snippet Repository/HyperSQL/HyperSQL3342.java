    private Preprocessor(String documentPath,
            String encoding, int options, IResolver resolver,
            String predefined) throws PreprocessorException {

        if (resolver == null) {
            File parentDir = new File(documentPath).getParentFile();

            this.resolver = new BasicResolver(parentDir);
        } else {
            this.resolver = resolver;
        }

        if (predefined == null || predefined.trim().length() == 0) {
            this.defines = new Defines();
        } else {
            predefined   = this.resolver.resolveProperties(predefined);
            this.defines = new Defines(predefined);
        }

        this.documentPath = documentPath;
        this.encoding     = encoding;
        this.options      = options;
        this.document     = new Document();
        this.stack        = new Stack();
        this.state        = CONDITION_NONE;
    }
