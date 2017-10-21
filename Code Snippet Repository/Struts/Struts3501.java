    public void init(PortletConfig cfg) throws PortletException {
        super.init(cfg);
        LOG.debug("Initializing portlet {}", getPortletName());

        Map<String, String> params = new HashMap<String, String>();
        for (Enumeration e = cfg.getInitParameterNames(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            String value = cfg.getInitParameter(name);
            params.put(name, value);
        }

        servletContext = new PortletServletContext(cfg.getPortletContext());
        dispatcherUtils = new Dispatcher(servletContext, params);
        dispatcherUtils.init();

        // For testability
        if (factory == null) {
            factory = dispatcherUtils.getContainer().getInstance(ActionProxyFactory.class);
        }
        portletNamespace = cfg.getInitParameter("portletNamespace");
        LOG.debug("PortletNamespace: {}", portletNamespace);

        parseModeConfig(actionMap, cfg, PortletMode.VIEW, "viewNamespace",
                "defaultViewAction");
        parseModeConfig(actionMap, cfg, PortletMode.EDIT, "editNamespace",
                "defaultEditAction");
        parseModeConfig(actionMap, cfg, PortletMode.HELP, "helpNamespace",
                "defaultHelpAction");
        parseModeConfig(actionMap, cfg, new PortletMode("config"), "configNamespace",
                "defaultConfigAction");
        parseModeConfig(actionMap, cfg, new PortletMode("about"), "aboutNamespace",
                "defaultAboutAction");
        parseModeConfig(actionMap, cfg, new PortletMode("print"), "printNamespace",
                "defaultPrintAction");
        parseModeConfig(actionMap, cfg, new PortletMode("preview"), "previewNamespace",
                "defaultPreviewAction");
        parseModeConfig(actionMap, cfg, new PortletMode("edit_defaults"),
                "editDefaultsNamespace", "defaultEditDefaultsAction");
        if (StringUtils.isEmpty(portletNamespace)) {
            portletNamespace = "";
        }

        container = dispatcherUtils.getContainer();
        actionMapper = container.getInstance(ActionMapper.class);
    }
