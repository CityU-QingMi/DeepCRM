    public TemplateEngine getTemplateEngine(Template template, String templateTypeOverride) {
        String templateType = DEFAULT_TEMPLATE_TYPE;
        String templateName = template.toString();
        if (StringUtils.contains(templateName, ".")) {
            templateType = StringUtils.substring(templateName, StringUtils.indexOf(templateName, ".") + 1);
        } else if (StringUtils.isNotBlank(templateTypeOverride)) {
            templateType = templateTypeOverride;
        } else {
            String type = defaultTemplateType;
            if (type != null) {
                templateType = type;
            }
        }
        return templateEngines.get(templateType).create();
    }
