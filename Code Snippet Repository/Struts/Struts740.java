    public MultiPartRequestWrapper(MultiPartRequest multiPartRequest, HttpServletRequest request,
                                   String saveDir, LocaleProvider provider,
                                   boolean disableRequestAttributeValueStackLookup) {
        super(request, disableRequestAttributeValueStackLookup);
        errors = new ArrayList<>();
        multi = multiPartRequest;
        defaultLocale = provider.getLocale();
        setLocale(request);
        try {
            multi.parse(request, saveDir);
            for (LocalizedMessage error : multi.getErrors()) {
                addError(error);
            }
        } catch (IOException e) {
            LOG.warn(e.getMessage(), e);
            addError(buildErrorMessage(e, new Object[] {e.getMessage()}));
        } 
    }
