    public void serviceAction(PortletRequest request, PortletResponse response, Map<String, Object> requestMap, Map<String, String[]> parameterMap,
                              Map<String, Object> sessionMap, Map<String, Object> applicationMap, String portletNamespace,
                              PortletPhase phase) throws PortletException {
        if (LOG.isDebugEnabled()) LOG.debug("serviceAction");
        Dispatcher.setInstance(dispatcherUtils);
        String actionName = null;
        String namespace;
        try {
            HttpServletRequest servletRequest = new PortletServletRequest(request, getPortletContext());
            HttpServletResponse servletResponse = createPortletServletResponse(response);
            if (phase.isAction()) {
                servletRequest = dispatcherUtils.wrapRequest(servletRequest);
                if (servletRequest instanceof MultiPartRequestWrapper) {
                    // Multipart request. Request parameters are encoded in the multipart data,
                    // so we need to manually add them to the parameter map.
                    parameterMap.putAll(servletRequest.getParameterMap());
                }
            }
            container.inject(servletRequest);
            ActionMapping mapping = getActionMapping(request, servletRequest);
            actionName = mapping.getName();
            if ("renderDirect".equals(actionName)) {
                namespace = request.getParameter(PortletConstants.RENDER_DIRECT_NAMESPACE);
            } else {
                namespace = mapping.getNamespace();
            }
            HashMap<String, Object> extraContext = createContextMap(requestMap, parameterMap,
                    sessionMap, applicationMap, request, response, servletRequest, servletResponse,
                    servletContext, getPortletConfig(), phase);
            extraContext.put(PortletConstants.ACTION_MAPPING, mapping);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Creating action proxy for name = " + actionName + ", namespace = " + namespace);
            }
            ActionProxy proxy = factory.createActionProxy(namespace, actionName, mapping.getMethod(), extraContext);
            request.setAttribute("struts.valueStack", proxy.getInvocation().getStack());
            proxy.execute();
        } catch (ConfigurationException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("Could not find action", e);
            }
            throw new PortletException("Could not find action " + actionName, e);
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("Could not execute action", e);
            }
            throw new PortletException("Error executing action " + actionName, e);
        } finally {
            Dispatcher.setInstance(null);
        }
    }
