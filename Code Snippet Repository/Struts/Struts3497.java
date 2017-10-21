    public void renderFormUrl(Form formComponent) {
        if (PortletActionContext.getPortletContext() == null) {
            servletRenderer.renderFormUrl(formComponent);
            return;
        }
        String namespace = formComponent.determineNamespace(formComponent.namespace, formComponent.getStack(),
                formComponent.request);
        String action;
        if (formComponent.action != null) {
            action = formComponent.findString(formComponent.action);
        } else {
            ActionInvocation ai = (ActionInvocation) formComponent.getStack().getContext().get(ActionContext.ACTION_INVOCATION);
            action = ai.getProxy().getActionName();
        }

        String type = "action";
        if (StringUtils.isNotEmpty(formComponent.method)) {
            if ("GET".equalsIgnoreCase(formComponent.method.trim())) {
                type = "render";
            }
        }
        if (action != null) {
            String result = portletUrlHelper.buildUrl(action, namespace, null,
                    formComponent.getParameters(), type, formComponent.portletMode, formComponent.windowState);
            formComponent.addParameter("action", result);


            // name/id: cut out anything between / and . should be the id and
            // name
            String id = formComponent.getId();
            if (id == null) {
                int slash = action.lastIndexOf('/');
                int dot = action.indexOf('.', slash);
                if (dot != -1) {
                    id = action.substring(slash + 1, dot);
                } else {
                    id = action.substring(slash + 1);
                }
                formComponent.addParameter("id", formComponent.escape(id));
            }
        }
    }
