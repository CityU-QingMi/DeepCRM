    private SharedThemeTemplateRendition createRendition(String templateId,
            ThemeMetadataTemplateRendition templateCodeMetadata) {
        SharedThemeTemplateRendition templateRendition = new SharedThemeTemplateRendition();

        // construct File object from path
        File templateFile = new File(this.themeDir + File.separator
                + templateCodeMetadata.getContentsFile());

        // read stylesheet contents
        String contents = loadTemplateFile(templateFile);
        if (contents == null) {
            // if we don't have any contents then load no string
            contents = "";
            log.error("Couldn't load stylesheet theme [" + this.getName()
                    + "] template file [" + templateFile + "]");
        }
        //TODO: remove templateId above
        templateRendition.setTemplate(contents);
        templateRendition.setTemplateLanguage(templateCodeMetadata.getTemplateLang());
        templateRendition.setType(templateCodeMetadata.getType());
        templateRendition.setLastModified(new Date(templateFile.lastModified()));

        return templateRendition;
    }
