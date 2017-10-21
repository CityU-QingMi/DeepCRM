     public ThemeTemplate getStylesheet() throws WebloggerException {
        // stylesheet is handled differently than other templates because with
        // the stylesheet we want to return the weblog custom version if it
        // exists, otherwise we return the shared theme version

        // load from theme first to see if we even support a stylesheet
        ThemeTemplate stylesheet = this.theme.getStylesheet();
        if (stylesheet != null) {
            // now try getting custom version from weblog
            ThemeTemplate override = WebloggerFactory.getWeblogger()
                    .getWeblogManager().getTemplateByAction(this.weblog, ComponentType.STYLESHEET);
            if (override != null) {
                stylesheet = override;
            }
        }
        return stylesheet;
    }
