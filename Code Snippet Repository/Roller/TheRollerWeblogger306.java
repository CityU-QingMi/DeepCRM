    public ThemeTemplate getTemplateByAction(ComponentType action) throws WebloggerException {
        
        if (action == null) {
            return null;
        }
        
        // NOTE: we specifically do *NOT* return templates by action from the
        // weblog's custom templates if the weblog is using a theme because we
        // don't want old templates to take effect when using a specific theme
        return this.theme.getTemplateByAction(action);
    }
