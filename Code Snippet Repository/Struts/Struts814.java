    public void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        LOG.debug("Forwarding to location: {}", finalLocation);

        PageContext pageContext = ServletActionContext.getPageContext();

        if (pageContext != null) {
            pageContext.include(finalLocation);
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher(finalLocation);

            //add parameters passed on the location to #parameters
            // see WW-2120
            if (StringUtils.isNotEmpty(finalLocation) && finalLocation.indexOf("?") > 0) {
                String queryString = finalLocation.substring(finalLocation.indexOf("?") + 1);
                HttpParameters parameters = getParameters(invocation);
                Map<String, Object> queryParams = urlHelper.parseQueryString(queryString, true);
                if (queryParams != null && !queryParams.isEmpty()) {
                    parameters = HttpParameters.create(queryParams).withParent(parameters).build();
                    invocation.getInvocationContext().setParameters(parameters);
                    // put to extraContext, see Dispatcher#createContextMap
                    invocation.getInvocationContext().getContextMap().put("parameters", parameters);
                }
            }

            // if the view doesn't exist, let's do a 404
            if (dispatcher == null) {
                LOG.warn("Location {} not found!", finalLocation);
                response.sendError(404, "result '" + finalLocation + "' not found");
                return;
            }

            //if we are inside an action tag, we always need to do an include
            Boolean insideActionTag = (Boolean) ObjectUtils.defaultIfNull(request.getAttribute(StrutsStatics.STRUTS_ACTION_TAG_INVOCATION), Boolean.FALSE);

            // If we're included, then include the view
            // Otherwise do forward
            // This allow the page to, for example, set content type
            if (!insideActionTag && !response.isCommitted() && (request.getAttribute("javax.servlet.include.servlet_path") == null)) {
                request.setAttribute("struts.view_uri", finalLocation);
                request.setAttribute("struts.request_uri", request.getRequestURI());

                dispatcher.forward(request, response);
            } else {
                dispatcher.include(request, response);
            }
        }
    }
