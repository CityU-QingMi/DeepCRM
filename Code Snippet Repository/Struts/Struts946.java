    public Object findTemplateSource(String name) throws IOException {
        int tokenIndex = (name == null) ? -1 : name.indexOf(themeExpansionToken);
        if (tokenIndex < 0) {
            return parentTemplateLoader.findTemplateSource(name);
        }

        int themeEndIndex = name.lastIndexOf('/');
        if (themeEndIndex < 0) {
            return parentTemplateLoader.findTemplateSource(name);
        }

        Template template = new Template(
            name.substring(0, tokenIndex - 1), 
            name.substring(tokenIndex + themeExpansionToken.length(), themeEndIndex), 
            name.substring(themeEndIndex + 1));
        
        List<Template> possibleTemplates = template.getPossibleTemplates(templateEngine);
        for (Template possibleTemplate : possibleTemplates) {
            Object templateSource = parentTemplateLoader.findTemplateSource(
                    possibleTemplate.toString().substring(1));
            if (templateSource != null) {
                return templateSource;
            }
        }
        String parentTheme = (String) templateEngine.getThemeProps(template).get("parent");
        if (parentTheme == null) {
            // no parent theme, no way to fetch parent template
            return null;
        }
        String parentName = "/" + template.getDir() + "/" + themeExpansionToken + parentTheme + "/" + template.getName();
        return this.findTemplateSource(parentName);
    }
