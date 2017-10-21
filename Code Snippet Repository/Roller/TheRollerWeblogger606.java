    public ThemeTemplate getWeblogPage() {

        if (weblogPage == null && weblogPageName != null) {
            try {
                weblogPage = getWeblog().getTheme().getTemplateByLink(
                        weblogPageName);
            } catch (WebloggerException ex) {
                log.error("Error getting weblog page " + weblogPageName, ex);
            }
        }

        return weblogPage;
    }
