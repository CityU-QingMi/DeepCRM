    private Parser(ParserController pc, JspReader reader, boolean isTagFile,
            boolean directivesOnly, URL jarFileUrl) {
        this.parserController = pc;
        this.ctxt = pc.getJspCompilationContext();
        this.pageInfo = pc.getCompiler().getPageInfo();
        this.err = pc.getCompiler().getErrorDispatcher();
        this.reader = reader;
        this.currentFile = reader.mark().getFile();
        this.scriptlessCount = 0;
        this.isTagFile = isTagFile;
        this.directivesOnly = directivesOnly;
        this.jarFileUrl = jarFileUrl;
        start = reader.mark();
    }
