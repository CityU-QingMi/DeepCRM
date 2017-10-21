    protected Compiler createCompiler(String className) {
        Compiler compiler = null; 
        try {
            compiler = (Compiler) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            log.warn(Localizer.getMessage("jsp.error.compiler"), e);
        } catch (IllegalAccessException e) {
            log.warn(Localizer.getMessage("jsp.error.compiler"), e);
        } catch (NoClassDefFoundError e) {
            if (log.isDebugEnabled()) {
                log.debug(Localizer.getMessage("jsp.error.compiler"), e);
            }
        } catch (ClassNotFoundException e) {
            if (log.isDebugEnabled()) {
                log.debug(Localizer.getMessage("jsp.error.compiler"), e);
            }
        }
        return compiler;
    }
