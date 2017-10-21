    protected void populateParams() {
        super.populateParams();

        Anchor tag = (Anchor) component;
        tag.setHref(href);
        tag.setIncludeParams(includeParams);
        tag.setScheme(scheme);
        tag.setValue(value);
        tag.setMethod(method);
        tag.setNamespace(namespace);
        tag.setAction(action);
        tag.setPortletMode(portletMode);
        tag.setPortletUrlType(portletUrlType);
        tag.setWindowState(windowState);
        tag.setAnchor(anchor);

        if (encode != null) {
            tag.setEncode(BooleanUtils.toBoolean(encode));
        }
        if (includeContext != null) {
            tag.setIncludeContext(BooleanUtils.toBoolean(includeContext));
        }
        if (escapeAmp != null) {
            tag.setEscapeAmp(BooleanUtils.toBoolean(escapeAmp));
        }
	    if (forceAddSchemeHostAndPort != null) {
            tag.setForceAddSchemeHostAndPort(BooleanUtils.toBoolean(forceAddSchemeHostAndPort));
        }
    }
