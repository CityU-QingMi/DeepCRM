    public void myPrepare() {
        ThemeManager themeMgr = WebloggerFactory.getWeblogger()
                .getThemeManager();
        themes = themeMgr.getEnabledThemesList();

        // See if we're using a shared theme with a custom template
        try {
            if (!WeblogTheme.CUSTOM.equals(getActionWeblog().getEditorTheme())
                    && getActionWeblog().getTheme().getStylesheet() != null) {

                ThemeTemplate override = WebloggerFactory
                        .getWeblogger()
                        .getWeblogManager()
                        .getTemplateByLink(
                                getActionWeblog(),
                                getActionWeblog().getTheme().getStylesheet()
                                        .getLink());
                if (override != null) {
                    sharedThemeCustomStylesheet = true;
                }
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up stylesheet on weblog - "
                    + getActionWeblog().getHandle(), ex);
        }
    }
