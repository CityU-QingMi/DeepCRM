    public ThemeTemplateWrapper getWeblogPage() {
        if(pageRequest.getWeblogPageName() != null) {
            return ThemeTemplateWrapper.wrap(pageRequest.getWeblogPage());
        } else {
            try {
                return ThemeTemplateWrapper.wrap(weblog.getTheme().getDefaultTemplate());
            } catch (WebloggerException ex) {
                log.error("Error getting default page", ex);
            }
        }
        return null;
    }
