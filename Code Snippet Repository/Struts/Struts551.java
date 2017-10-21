    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();

        if (href != null)
            addParameter("href", ensureAttributeSafelyNotEscaped(findString(href)));
        else {
            //no href, build it from URL attributes
            StringWriter sw = new StringWriter();
            urlRenderer.beforeRenderUrl(urlProvider);
            urlRenderer.renderUrl(sw, urlProvider);
            String builtHref = sw.toString();
            if (StringUtils.isNotEmpty(builtHref))
                addParameter("href", ensureAttributeSafelyNotEscaped(builtHref));
        }
    }
