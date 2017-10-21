    protected void populateParams() {
        super.populateParams();
        Form form = ((Form) component);
        form.setAction(action);
        form.setTarget(target);
        form.setEnctype(enctype);
        form.setMethod(method);
        form.setNamespace(namespace);
        form.setValidate(validate);
        form.setOnreset(onreset);
        form.setOnsubmit(onsubmit);
        form.setPortletMode(portletMode);
        form.setWindowState(windowState);
        form.setAcceptcharset(acceptcharset);
        form.setFocusElement(focusElement);
        form.setIncludeContext(includeContext);
    }
