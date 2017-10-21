    public Renderer getRenderer(Template template) {        
        Renderer renderer = null;
        if(template.getTemplateLanguage() == null || template.getId() == null) {
            return null;
        }        
        if("groovlet".equals(template.getTemplateLanguage()) && template instanceof WeblogTemplate) {             
            try {
               renderer = new GroovletRenderer((WeblogTemplate)template);
            } catch(Exception ex) {
                return null;
            }                        
        }        
        return renderer;
    }
