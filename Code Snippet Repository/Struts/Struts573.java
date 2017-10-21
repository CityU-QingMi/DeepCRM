    @Override
    protected void evaluateExtraParams() {
        super.evaluateExtraParams();
        if (validate != null) {
            addParameter("validate", findValue(validate, Boolean.class));
        }

        if (name == null) {
            //make the name the same as the id
            String id = (String) getParameters().get("id");
             if (StringUtils.isNotEmpty(id)) {
                addParameter("name", id);
             }
        }

        if (onsubmit != null) {
            addParameter("onsubmit", findString(onsubmit));
        }

        if (onreset != null) {
            addParameter("onreset", findString(onreset));
        }

        if (target != null) {
            addParameter("target", findString(target));
        }

        if (enctype != null) {
            addParameter("enctype", findString(enctype));
        }

        if (method != null) {
            addParameter("method", findString(method));
        }

        if (acceptcharset != null) {
            addParameter("acceptcharset", findString(acceptcharset));
        }

        // keep a collection of the tag names for anything special the templates might want to do (such as pure client
        // side validation)
        if (!parameters.containsKey("tagNames")) {
            // we have this if check so we don't do this twice (on open and close of the template)
            addParameter("tagNames", new ArrayList());
        }

        if (focusElement != null) {
            addParameter("focusElement", findString(focusElement));
        }
    }
