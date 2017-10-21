    public void copyTo(WeblogTemplate dataHolder) throws WebloggerException {

        if (dataHolder.getTemplateRendition(RenditionType.STANDARD) != null) {
            // if we have a template, then set it
            CustomTemplateRendition tc = dataHolder.getTemplateRendition(RenditionType.STANDARD);
            tc.setTemplate(contentsStandard);
            WebloggerFactory.getWeblogger().getWeblogManager().saveTemplateRendition(tc);
        } else { 
            // otherwise create it, then set it
            CustomTemplateRendition tc = new CustomTemplateRendition(dataHolder, RenditionType.STANDARD);
			tc.setTemplate("");
            WebloggerFactory.getWeblogger().getWeblogManager().saveTemplateRendition(tc);
        }

        if (dataHolder.getTemplateRendition(RenditionType.MOBILE) != null) {
            CustomTemplateRendition tc = dataHolder.getTemplateRendition(RenditionType.MOBILE);
            tc.setTemplate(contentsMobile);
            WebloggerFactory.getWeblogger().getWeblogManager().saveTemplateRendition(tc);
        }

        // the rest of the template properties can be modified only when
        // dealing with a CUSTOM weblog template
        if (dataHolder.isCustom()) {
            dataHolder.setName(getName());
            dataHolder.setAction(getAction());
            dataHolder.setDescription(getDescription());
            dataHolder.setLink(getLink());
            dataHolder.setNavbar(isNavbar());
            dataHolder.setHidden(isHidden());
        }
    }
