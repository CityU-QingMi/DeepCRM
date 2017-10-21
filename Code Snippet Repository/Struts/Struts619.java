    public String getTemplateDir() {
        String templateDir = null;

        if (this.templateDir != null) {
            templateDir = findString(this.templateDir);
        }

        // If templateDir is not explicitly given,
        // try to find attribute which states the dir set to use
        if (StringUtils.isBlank(templateDir)) {
            templateDir = stack.findString("#attr.templateDir");
        }

        // Default template set
        if (StringUtils.isBlank(templateDir)) {
            templateDir = defaultTemplateDir;
        }

        // Defaults to 'template'
        if (StringUtils.isBlank(templateDir)) {
            templateDir = "template";
        }

        return templateDir;
    }
