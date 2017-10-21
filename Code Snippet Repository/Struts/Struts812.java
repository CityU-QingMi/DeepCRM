    protected String makePostbackUri(ActionInvocation invocation) {
        ActionContext ctx = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
        String postbackUri;

        if (actionName != null) {
            actionName = conditionalParse(actionName, invocation);
            if (namespace == null) {
                namespace = invocation.getProxy().getNamespace();
            } else {
                namespace = conditionalParse(namespace, invocation);
            }
            if (method == null) {
                method = "";
            } else {
                method = conditionalParse(method, invocation);
            }
            postbackUri = request.getContextPath() + actionMapper.getUriFromActionMapping(new ActionMapping(actionName, namespace, method, null));
        } else {
            String location = getLocation();
            // Do not prepend if the URL is a FQN
            if (!location.matches("^([a-zA-z]+:)?//.*")) {
                // If the URL is relative to the servlet context, prepend the servlet context path
                if (prependServletContext && (request.getContextPath() != null) && (request.getContextPath().length() > 0)) {
                    location = request.getContextPath() + location;
                }
            }
            postbackUri = location;
        }

        return postbackUri;
    }
