    public GuiceWebloggerProvider() {
        
        String moduleClassname = WebloggerConfig.getProperty("guice.backend.module");
        if(moduleClassname == null) {
            throw new NullPointerException("unable to lookup default guice module via property 'guice.backend.module'");
        }
        
        try {
            Class moduleClass = Class.forName(moduleClassname);
            Module module = (Module)moduleClass.newInstance();
            injector = Guice.createInjector(module);
        } catch (ThreadDeath t) {
            throw t;
        } catch (Throwable e) {
            // Fatal misconfiguration, cannot recover
            throw new RuntimeException("Error instantiating backend module " + moduleClassname + "; exception message: " + e.getMessage(), e);
        }
    }
