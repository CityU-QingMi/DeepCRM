    public static void main(String[] args) {
        Map<String, String> configurationOverrides = new HashMap<String, String>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolePU", configurationOverrides);
        EntityManager entityManager = emf.createEntityManager();

        TestConsole console = new TestConsole(entityManager);

        System.out.println("");
        System.out.println("Welcome to EntityVersions demo!");
//      If you would like to use HSQLDB, uncomment relevant entries in
//      hibernate-envers/src/demo/resources/META-INF/persistence.xml descriptor and add required JAR libraries.
//        String userDbFile = System.getProperty("java.io.tmpdir") + File.separator + "_versions_demo.db";
//        System.out.println("HSQLDB database file location: " + userDbFile);

        console.populateTestData();
        console.start();

        entityManager.close();
        emf.close();
    }
