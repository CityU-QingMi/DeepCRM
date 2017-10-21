    public void evaluateParams() {
        super.evaluateParams();

        Form form = (Form) findAncestor(Form.class);
        if (form != null) {
            String encType = (String) form.getParameters().get("enctype");
            if (!"multipart/form-data".equals(encType)) {
                // uh oh, this isn't good! Let's warn the developer
                LOG.warn("Struts has detected a file upload UI tag (s:file) being used without a form set to enctype 'multipart/form-data'. This is probably an error!");
            }

            String method = (String) form.getParameters().get("method");
            if (!"post".equalsIgnoreCase(method)) {
                // uh oh, this isn't good! Let's warn the developer
                LOG.warn("Struts has detected a file upload UI tag (s:file) being used without a form set to method 'POST'. This is probably an error!");
            }
        }

        if (accept != null) {
            addParameter("accept", findString(accept));
        }

        if (size != null) {
            addParameter("size", findString(size));
        }
    }
