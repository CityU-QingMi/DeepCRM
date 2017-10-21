    private Preprocessor(Preprocessor other, Document include) {
        this.document     = include;
        this.encoding     = other.encoding;
        this.stack        = new Stack();
        this.state        = CONDITION_NONE;
        this.options      = other.options;
        this.documentPath = other.documentPath;
        this.resolver     = other.resolver;
        this.defines      = other.defines;
    }
