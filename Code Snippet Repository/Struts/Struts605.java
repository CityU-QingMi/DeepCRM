    public void beforeRenderUrl(UrlProvider urlComponent) {
        if (urlComponent.getValue() != null) {
            urlComponent.setValue(urlComponent.findString(urlComponent.getValue()));
        }

        // no explicit url set so attach params from current url, do
        // this at start so body params can override any of these they wish.
        try {
            // ww-1266
            String includeParams = (urlComponent.getUrlIncludeParams() != null ? urlComponent.getUrlIncludeParams().toLowerCase() : UrlProvider.GET);

            if (urlComponent.getIncludeParams() != null) {
                includeParams = urlComponent.findString(urlComponent.getIncludeParams());
            }

            if (UrlProvider.NONE.equalsIgnoreCase(includeParams)) {
                mergeRequestParameters(urlComponent.getValue(), urlComponent.getParameters(), Collections.<String, Object>emptyMap());
            } else if (UrlProvider.ALL.equalsIgnoreCase(includeParams)) {
                mergeRequestParameters(urlComponent.getValue(), urlComponent.getParameters(), urlComponent.getHttpServletRequest().getParameterMap());

                // for ALL also include GET parameters
                includeGetParameters(urlComponent);
                includeExtraParameters(urlComponent);
            } else if (UrlProvider.GET.equalsIgnoreCase(includeParams) || (includeParams == null && urlComponent.getValue() == null && urlComponent.getAction() == null)) {
                includeGetParameters(urlComponent);
                includeExtraParameters(urlComponent);
            } else if (includeParams != null) {
                LOG.warn("Unknown value for includeParams parameter to URL tag: {}", includeParams);
            }
        } catch (Exception e) {
            LOG.warn("Unable to put request parameters ({}) into parameter map.", urlComponent.getHttpServletRequest().getQueryString(), e);
        }
    }
