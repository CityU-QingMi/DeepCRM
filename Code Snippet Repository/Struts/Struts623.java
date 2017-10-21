    protected void populateComponentHtmlId(Form form) {
        String tryId;
        String generatedId;
        if (id != null) {
            // this check is needed for backwards compatibility with 2.1.x
            tryId = findStringIfAltSyntax(id);
        } else if (null == (generatedId = escape(name != null ? findString(name) : null))) {
            LOG.debug("Cannot determine id attribute for [{}], consider defining id, name or key attribute!", this);
            tryId = null;
        } else if (form != null) {
            tryId = form.getParameters().get("id") + "_" + generatedId;
        } else {
            tryId = generatedId;
        }
        
        //fix for https://issues.apache.org/jira/browse/WW-4299
        //do not assign value to id if tryId is null
        if (tryId != null) {
          addParameter("id", tryId);
          addParameter("escapedId", escape(tryId));
        }
    }
