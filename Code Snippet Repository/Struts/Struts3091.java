    protected void init( ServletConfig config ) {
        int maxSize=-1;
        String maxSizeS=getOption(config, OPTION_MAXSIZE, null);
        if( maxSizeS != null ) {
            try {
                maxSize=Integer.parseInt(maxSizeS);
            } catch( Exception ex) {
                maxSize=-1;
            }
        }
        if( maxSize <0  ) {
            maxSize=Constants.MAX_POOL_SIZE;
        }
        this.handlers = new Tag[maxSize];
        this.current = -1;
        this.annotationProcessor = 
            (AnnotationProcessor) config.getServletContext().getAttribute(AnnotationProcessor.class.getName());
    }
