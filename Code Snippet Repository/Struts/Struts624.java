    public Map getThemeProps(Template template) {
        Properties props = themeProps.get(template.getTheme());
        if (props == null) {
            synchronized (themeProps) {
                props = readNewProperties(template);
                themeProps.put(template.getTheme(), props);
            }
        }
        return props;
    }
