    public Renderer getRenderer(Template template) {
        Renderer renderer = null;
        if(template.getTemplateLanguage() == null || template.getId() == null) {
            return null;
        }
        if("gsp".equals(template.getTemplateLanguage()) && template instanceof WeblogTemplate) {
            try {
                renderer = new GSPRenderer(templateEngine, (WeblogTemplate)template); 
            } catch(Exception ex) {
                return null;
            }
        }
        return renderer;
    }
