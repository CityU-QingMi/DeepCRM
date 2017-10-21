    public void copyFrom(WeblogTemplate dataHolder) throws WebloggerException {
        this.id = dataHolder.getId();
        this.name = dataHolder.getName();
        this.action = dataHolder.getAction();
        this.description = dataHolder.getDescription();
        this.link = dataHolder.getLink();

        if (dataHolder.getTemplateRendition(RenditionType.STANDARD) != null) {
            this.contentsStandard = dataHolder.getTemplateRendition(RenditionType.STANDARD).getTemplate();
        } else {
            this.contentsStandard = "";
        }
        if (dataHolder.getTemplateRendition(RenditionType.MOBILE) != null) {
            this.contentsMobile = dataHolder.getTemplateRendition(RenditionType.MOBILE).getTemplate();
        }
		log.debug("Standard: " + this.contentsStandard + " Mobile: " + this.contentsMobile); 

        this.navbar = dataHolder.isNavbar();
        this.hidden = dataHolder.isHidden();
        setManualContentType(dataHolder.getOutputContentType());
        if(getManualContentType() != null) {
            setAutoContentType(Boolean.FALSE);
        }
    }
