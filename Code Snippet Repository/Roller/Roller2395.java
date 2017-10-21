    public String execute() {
        if (template != null) {
            try {
                if (getTemplate().getTemplateRendition(RenditionType.STANDARD) != null) {
                    setContentsStandard(getTemplate().getTemplateRendition(
                            RenditionType.STANDARD).getTemplate());
                } else {
                    setContentsStandard("");
                }
                if (getTemplate().getTemplateRendition(RenditionType.MOBILE) != null) {
                    setContentsMobile(getTemplate().getTemplateRendition(
                            RenditionType.MOBILE).getTemplate());
                }
                if (log.isDebugEnabled()) {
                    log.debug("Standard: " + getContentsStandard() + " Mobile: "
                            + getContentsMobile());
                }
            } catch (WebloggerException e) {
                log.error("Error loading Weblog template codes for stylesheet", e);
            }
        }
        return INPUT;
    }
