    public void render(Map model, Writer writer) throws RenderingException {
        try {
            long startTime = System.currentTimeMillis();            
            Binding binding = new GroovyRollerBinding(model, writer);
            GroovyShell shell = new GroovyShell(binding);
            shell.evaluate(template.getContents());              
            long endTime = System.currentTimeMillis();

            long renderTime = (endTime - startTime)/1000;
            log.debug("Rendered ["+template.getId()+"] in "+renderTime+" secs"); 
            
        } catch (Throwable ex) {
            log.debug("Executing Groovy script", ex);
            renderThrowable(ex, writer);
        }
    }
