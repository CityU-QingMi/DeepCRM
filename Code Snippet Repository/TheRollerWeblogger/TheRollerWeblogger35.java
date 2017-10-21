    public static void main(String[] args) throws Exception {
        
        RefreshPlanetTask task = new RefreshPlanetTask();
        task.initialize();
        
        // need to prepare and bootstrap Planet as well
        WebloggerStartup.prepare();
        WebloggerFactory.bootstrap();
        
        task.run();
    }
