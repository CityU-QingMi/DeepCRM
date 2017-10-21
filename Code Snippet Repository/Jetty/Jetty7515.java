    public static void main(String[] args) throws Exception
    {
        String target="target/test-spec-preconfigured";
        File file = new File(target);
        if (file.exists())
            IO.delete(file);
        
        File realmPropertiesDest = new File ("target/test-spec-realm.properties");
        if (realmPropertiesDest.exists())
            IO.delete(realmPropertiesDest);
        
        Resource realmPropertiesSrc = Resource.newResource("src/test/resources/realm.properties");
        realmPropertiesSrc.copyTo(realmPropertiesDest);
        System.setProperty("jetty.home", "target");
        
        PreconfigureQuickStartWar.main("target/test-spec.war",target, "src/test/resources/test-spec.xml");

        LOG.info("Preconfigured in {}ms",TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-__start));
        
        // IO.copy(new FileInputStream("target/test-spec-preconfigured/WEB-INF/quickstart-web.xml"),System.out);
    }
