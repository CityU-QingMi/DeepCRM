    public Renderer getRenderer(Template template) {        
        Renderer renderer = null;
        if(template.getTemplateLanguage() == null || template.getId() == null) {
            return null;
        }        
        if(bsfLanguages.contains(template.getTemplateLanguage())) {             
            try {
               renderer = new BSFRenderer(template);
            } catch(Exception ex) {
                return null;
            }                        
        }      
        return renderer;
    }    
