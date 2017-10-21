    @SkipValidation
    public String execute() {
        try {
            if (getTemplate() == null) {
                addError("Unable to locate specified template");
                return LIST;
            }
            WeblogTemplate page = getTemplate();
            getBean().copyFrom(template);

            // empty content-type indicates that page uses auto content-type detection
            if (StringUtils.isEmpty(page.getOutputContentType())) {
                getBean().setAutoContentType(Boolean.TRUE);
            } else {
                getBean().setAutoContentType(Boolean.FALSE);
                getBean().setManualContentType(page.getOutputContentType());
            }

        } catch (WebloggerException ex) {
           log.error("Error updating page - " + getBean().getId(), ex);
           addError("Error saving template - check Roller logs");
        }

        return INPUT;
    }
