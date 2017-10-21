    private void switchDevMode(LocatableProperties props) {
        if ("true".equalsIgnoreCase(props.getProperty(StrutsConstants.STRUTS_DEVMODE))) {
            if (props.getProperty(StrutsConstants.STRUTS_I18N_RELOAD) == null) {
                props.setProperty(StrutsConstants.STRUTS_I18N_RELOAD, "true");
            }
            if (props.getProperty(StrutsConstants.STRUTS_CONFIGURATION_XML_RELOAD) == null) {
                props.setProperty(StrutsConstants.STRUTS_CONFIGURATION_XML_RELOAD, "true");
            }
            if (props.getProperty(StrutsConstants.STRUTS_FREEMARKER_TEMPLATES_CACHE_UPDATE_DELAY) == null) {
                props.setProperty(StrutsConstants.STRUTS_FREEMARKER_TEMPLATES_CACHE_UPDATE_DELAY, "0");
            }
            // Convert struts properties into ones that xwork expects
            props.setProperty(XWorkConstants.DEV_MODE, "true");
        } else {
            props.setProperty(XWorkConstants.DEV_MODE, "false");
        }
    }
