     public ThemeTemplate getTemplateByLink(String link) throws WebloggerException {

        if (link == null) {
            return null;
        }

        ThemeTemplate template;

        // if name refers to the stylesheet then return result of getStylesheet()
        ThemeTemplate stylesheet = getStylesheet();
        if(stylesheet != null && link.equals(stylesheet.getLink())) {
            return stylesheet;
        }

        // first check if this user has selected a theme
        // if so then return the proper theme template
        template = this.theme.getTemplateByLink(link);

        // if we didn't get the Template from a theme then look in the db
        if(template == null) {
            template = WebloggerFactory.getWeblogger()
                    .getWeblogManager().getTemplateByLink(this.weblog, link);
        }

        return template;
    }
