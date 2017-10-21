    public String getTheme() {
        String theme = null;

        if (this.theme != null) {
            theme = findString(this.theme);
        }

        if (StringUtils.isBlank(theme)) {
            Form form = (Form) findAncestor(Form.class);
            if (form != null) {
                theme = form.getTheme();
            }
        }

        // If theme set is not explicitly given,
        // try to find attribute which states the theme set to use
        if (StringUtils.isBlank(theme)) {
            theme = stack.findString("#attr.theme");
        }

        // Default theme set
        if (StringUtils.isBlank(theme)) {
            theme = defaultUITheme;
        }

        return theme;
    }
