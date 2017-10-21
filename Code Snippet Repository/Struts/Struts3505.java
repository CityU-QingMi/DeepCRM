    public HashMap<String, Object> createContextMap(Map<String, Object> requestMap, Map<String, String[]> parameterMap,
                                                    Map<String, Object> sessionMap, Map<String, Object> applicationMap,
                                                    PortletRequest request, PortletResponse response, HttpServletRequest servletRequest,
                                                    HttpServletResponse servletResponse, ServletContext servletContext,
                                                    PortletConfig portletConfig, PortletPhase phase) throws IOException {

        // TODO Must put http request/response objects into map for use with
        container.inject(servletRequest);

        // ServletActionContext
        HashMap<String, Object> extraContext = new HashMap<String, Object>();
        // The dummy servlet objects. Eases reuse of existing interceptors that uses the servlet objects.
        extraContext.put(StrutsStatics.HTTP_REQUEST, servletRequest);
        extraContext.put(StrutsStatics.HTTP_RESPONSE, servletResponse);
        extraContext.put(StrutsStatics.SERVLET_CONTEXT, servletContext);
        // End dummy servlet objects
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(parameterMap).build());
        extraContext.put(ActionContext.SESSION, sessionMap);
        extraContext.put(ActionContext.APPLICATION, applicationMap);

        extraContext.put(ActionContext.LOCALE, getLocale(request));

        extraContext.put(StrutsStatics.STRUTS_PORTLET_CONTEXT, getPortletContext());
        extraContext.put(REQUEST, request);
        extraContext.put(RESPONSE, response);
        extraContext.put(PORTLET_CONFIG, portletConfig);
        extraContext.put(PORTLET_NAMESPACE, portletNamespace);
        extraContext.put(DEFAULT_ACTION_FOR_MODE, actionMap.get(request.getPortletMode()));
        // helpers to get access to request/session/application scope
        extraContext.put("request", requestMap);
        extraContext.put("session", sessionMap);
        extraContext.put("application", applicationMap);
        extraContext.put("parameters", parameterMap);
        extraContext.put(MODE_NAMESPACE_MAP, modeMap);
        extraContext.put(PortletConstants.DEFAULT_ACTION_MAP, actionMap);

        extraContext.put(PortletConstants.PHASE, phase);

        AttributeMap attrMap = new AttributeMap(extraContext);
        extraContext.put("attr", attrMap);

        return extraContext;
    }
