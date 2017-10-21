    protected Object createUrl( String scheme, String type, Map<String, String[]> portletParams ) {
        RenderResponse response = PortletActionContext.getRenderResponse();
        PortletURL url;
        if (URLTYPE_NAME_ACTION.equalsIgnoreCase(type)) {
            if (LOG.isDebugEnabled()) LOG.debug("Creating action url");
            url = response.createActionURL();
        }
        else {
            if (LOG.isDebugEnabled()) LOG.debug("Creating render url");
            url = response.createRenderURL();
        }

        url.setParameters(portletParams);

        if ("HTTPS".equalsIgnoreCase(scheme)) {
            try {
                url.setSecure(true);
            } catch ( PortletSecurityException e) {
                LOG.error("Cannot set scheme to https", e);
            }
        }
        return url;
    }
