    public void initialize() {
        loadHelpers = new LinkedList<ClassLoadHelper>();

        loadHelpers.add(new LoadingLoaderClassLoadHelper());
        loadHelpers.add(new SimpleClassLoadHelper());
        loadHelpers.add(new ThreadContextClassLoadHelper());
        loadHelpers.add(new InitThreadContextClassLoadHelper());
        
        for(ClassLoadHelper loadHelper: loadHelpers) {
            loadHelper.initialize();
        }
    }
