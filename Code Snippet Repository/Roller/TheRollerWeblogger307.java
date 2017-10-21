    public ThemeTemplate getTemplateByName(String name) throws WebloggerException {
        
        if (name == null) {
            return null;
        }
        
        ThemeTemplate template;
        
        // if name refers to the stylesheet then return result of getStylesheet()
        ThemeTemplate stylesheet = getStylesheet();
        if (stylesheet != null && name.equals(stylesheet.getName())) {
            return stylesheet;
        }
        
        // first check if this user has selected a theme
        // if so then return the proper theme template
        template = this.theme.getTemplateByName(name);
        
        // if we didn't get the Template from a theme then look in the db
        if (template == null) {
            template = WebloggerFactory.getWeblogger().getWeblogManager().getTemplateByName(this.weblog, name);
        }
        
        return template;
    }
