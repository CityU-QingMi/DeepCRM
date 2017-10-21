    private String prependNamespace(String namespace, String portletMode, boolean prependModeNamespace) {
        StringBuilder sb = new StringBuilder();
        String modeNamespace;
        if (prependModeNamespace) {
            PortletMode mode = PortletActionContext.getRequest().getPortletMode();
            if(StringUtils.isNotEmpty(portletMode)) {
                mode = new PortletMode(portletMode);
            }
            modeNamespace = PortletActionContext.getModeNamespaceMap().get(mode);
        } else {
            modeNamespace = null;
        }
        String portletNamespace = PortletActionContext.getPortletNamespace();
        if (LOG.isDebugEnabled()) {
            LOG.debug("PortletNamespace: " + portletNamespace + ", modeNamespace: "
                    + (modeNamespace!=null ? modeNamespace : "IGNORED"));
        }
        if(StringUtils.isNotEmpty(portletNamespace)) {
            sb.append(portletNamespace);
        }
        if(StringUtils.isNotEmpty(modeNamespace)) {
            if(!modeNamespace.startsWith("/")) {
                sb.append("/");
            }
            sb.append(modeNamespace);
        }
        if(StringUtils.isNotEmpty(namespace)) {
            if(!namespace.startsWith("/")) {
                sb.append("/");
            }
            sb.append(namespace);
        }
        if (LOG.isDebugEnabled()) LOG.debug("Resulting namespace: " + sb);
        return sb.toString();
    }
