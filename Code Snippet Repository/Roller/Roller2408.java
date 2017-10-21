    public String execute() {

        // set theme to current value
        if (WeblogTheme.CUSTOM.equals(getActionWeblog().getEditorTheme())) {
            setThemeId(null);
            setSelectedThemeId(null);
            setImportTheme(false);
        } else {
            setThemeId(getActionWeblog().getTheme().getId());
            setSelectedThemeId(getThemeId());
        }

        return INPUT;
    }
