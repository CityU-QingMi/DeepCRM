    protected void enableAncestorFormCustomOnsubmit() {
        Form form = (Form) findAncestor(Form.class);
        if (form != null) {
            form.addParameter("customOnsubmitEnabled", Boolean.TRUE);
        } else {
            if (LOG.isWarnEnabled()) {
        	LOG.warn("Cannot find an Ancestor form, custom onsubmit is NOT enabled");
            }
        }
    }
