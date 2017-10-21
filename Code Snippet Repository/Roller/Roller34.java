    public void render(Map model, Writer writer) throws RenderingException {
        try {
            long startTime = System.currentTimeMillis();
            
            BSFManager manager = new BSFManager();            
            for (Iterator it = model.keySet().iterator(); it.hasNext();) {
                String key = (String)it.next();
                manager.declareBean(key, model.get(key), model.get(key).getClass());
                manager.registerBean(key, model.get(key));
            }
            manager.declareBean("out", writer, Writer.class);
            manager.registerBean("out", writer);
            manager.exec(template.getTemplateLanguage(), 
                    "(java)", 1, 1, template.getContents());

            long endTime = System.currentTimeMillis();
            long renderTime = (endTime - startTime)/1000;
            log.debug("Rendered ["+template.getId()+"] with language ["
                    +template.getTemplateLanguage()+"] in "+renderTime+" secs"); 
            
        } catch (BSFException ex) {
            log.debug("Executing BSF script", ex);
            renderThrowable(ex, writer);
        }
        finally {}
    }
