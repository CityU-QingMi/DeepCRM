    public List<Template> getPossibleTemplates(TemplateEngine engine) {
        List<Template> list = new ArrayList<Template>(3);
        Template template = this;
        String parentTheme;
        list.add(template);
        while ((parentTheme = (String) engine.getThemeProps(template).get("parent")) != null) {
            try {
                template = (Template) template.clone();
                template.theme = parentTheme;
                list.add(template);
            } catch (CloneNotSupportedException e) {
                // do nothing
            }
        }

        return list;
    }
