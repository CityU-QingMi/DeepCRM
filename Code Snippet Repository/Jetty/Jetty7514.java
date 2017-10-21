    public static void main(String[] args) throws Exception
    {
        String target="target/test-jndi-preconfigured";
        File file = new File(target);
        if (file.exists())
            IO.delete(file);
        
        PreconfigureQuickStartWar.main("target/test-jndi.war",target, "src/test/resources/test-jndi.xml");

        LOG.info("Preconfigured in {}ms",TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-__start));
        
        // IO.copy(new FileInputStream("target/test-jndi-preconfigured/WEB-INF/quickstart-web.xml"),System.out);
    }
