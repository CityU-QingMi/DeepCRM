    public void render(Map model, Writer writer) throws RenderingException {
        try {
            if (parseException == null) {                

                long startTime = System.currentTimeMillis();
                Binding binding = new GroovyRollerBinding(model, writer);
                groovyTemplate.make(binding.getVariables()).writeTo(writer);
                long endTime = System.currentTimeMillis();
                
                long renderTime = (endTime - startTime)/1000;
                log.debug("Rendered ["+template.getId()+"] in "+renderTime+" secs");
            } else {
                renderThrowable(parseException, writer);
            }
            
        } catch (Exception ex) {
            log.debug("Executing Groovy template", ex);
            renderThrowable(ex, writer);
        }
    }
