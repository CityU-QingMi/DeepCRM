    private void myValidate() {

        // if name changed make sure there isn't a conflict
        if (!getTemplate().getName().equals(getBean().getName())) {
            try {
                if (WebloggerFactory.getWeblogger().getWeblogManager()
                    .getTemplateByName(getActionWeblog(), getBean().getName()) != null) {
                    addError("pagesForm.error.alreadyExists", getBean().getName());
                }
            } catch (WebloggerException ex) {
                log.error("Error checking page name uniqueness", ex);
            }
        }

        // if link changed make sure there isn't a conflict
        if (!StringUtils.isEmpty(getBean().getLink()) &&
                !getBean().getLink().equals(getTemplate().getLink())) {
            try {
                if (WebloggerFactory.getWeblogger().getWeblogManager()
                        .getTemplateByLink(getActionWeblog(), getBean().getLink()) != null) {
                    addError("pagesForm.error.alreadyExists", getBean().getLink());
                }
            } catch (WebloggerException ex) {
                log.error("Error checking page link uniqueness", ex);
            }
        }
    }
