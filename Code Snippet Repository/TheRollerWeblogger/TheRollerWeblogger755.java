    public String delete() {
        if (template != null && sharedTheme && !hasActionErrors()) {
            try {
                // Delete template and flush
                WeblogManager mgr = WebloggerFactory.getWeblogger()
                        .getWeblogManager();

                // Remove template and page codes
                mgr.removeTemplate(template);

                Weblog weblog = getActionWeblog();

                // save updated weblog and flush
                mgr.saveWeblog(weblog);

                // notify caches
                CacheManager.invalidate(template);

                // Flush for operation
                WebloggerFactory.getWeblogger().flush();

                // success message
                addMessage("stylesheetEdit.default.success",
                        template.getName());

                template = null;
                sharedStylesheetDeleted = true;

            } catch (Exception e) {
                log.error("Error deleting stylesheet template for weblog - "
                        + getActionWeblog().getHandle(), e);
                addError("generic.error.check.logs");
            }
        }
        return INPUT;
    }
