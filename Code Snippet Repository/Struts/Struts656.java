    public HttpServletRequest wrapRequest(HttpServletRequest request) throws IOException {
        // don't wrap more than once
        if (request instanceof StrutsRequestWrapper) {
            return request;
        }

        if (isMultipartSupportEnabled(request) && isMultipartRequest(request)) {
            MultiPartRequest multiPartRequest = getMultiPartRequest();
            LocaleProviderFactory localeProviderFactory = getContainer().getInstance(LocaleProviderFactory.class);

            request = new MultiPartRequestWrapper(
                    multiPartRequest,
                    request,
                    getSaveDir(),
                    localeProviderFactory.createLocaleProvider(),
                    disableRequestAttributeValueStackLookup
            );
        } else {
            request = new StrutsRequestWrapper(request, disableRequestAttributeValueStackLookup);
        }

        return request;
    }
