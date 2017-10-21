    public Renderer getRenderer(Template template, 
			MobileDeviceRepository.DeviceType deviceType) {
        Renderer renderer = null;
        TemplateRendition tr;

        if (template == null || template.getId() == null) {
            return null;
        }

        // nothing we can do with null values
        try {
            tr = template.getTemplateRendition(RenditionType.STANDARD);
        } catch (WebloggerException e) {
            return null;
        }

        if (tr == null) {
            return null;
        }
        
        if (TemplateLanguage.VELOCITY.equals(tr.getTemplateLanguage())) {
            // standard velocity template
            try {
               renderer = new VelocityRenderer(template, deviceType);
            } catch(Exception ex) {
				log.error("ERROR creating VelocityRenderer", ex);
                // some kind of exception so we don't have a renderer
                // we do catching/logging in VelocityRenderer constructor
                return null;
            }            
        }
        return renderer;
    }
