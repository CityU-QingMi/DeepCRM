    public static Renderer getRenderer(Template template, MobileDeviceRepository.DeviceType deviceType)
            throws RenderingException {

        Renderer renderer;

        // iterate over our renderer factories and see if one of them
        // wants to handle this content
        for (RendererFactory rendererFactory : rendererFactories) {
            renderer = rendererFactory.getRenderer(template, deviceType);
            if (renderer != null) {
                return renderer;
            }
        }

        throw new RenderingException("No renderer found for template "
                + template.getId() + "!");
    }
