    @Override
    protected void before() throws Throwable {
        final Path root = Files.createTempDirectory("cassandra");
        Files.createDirectories(root.resolve("data"));
        final Path config = root.resolve("cassandra.yml");
        Files.copy(getClass().getResourceAsStream("/cassandra.yaml"), config);
        System.setProperty("cassandra.config", "file:" + config.toString());
        System.setProperty("cassandra.storagedir", root.toString());
        System.setProperty("cassandra-foreground", "true"); // prevents Cassandra from closing stdout/stderr
        THREAD_FACTORY.newThread(embeddedCassandra).start();
        latch.await();
        cluster = Cluster.builder().addContactPoints(InetAddress.getLoopbackAddress()).build();
        try (final Session session = cluster.connect()) {
            session.execute("CREATE KEYSPACE " + keyspace + " WITH REPLICATION = " +
                "{ 'class': 'SimpleStrategy', 'replication_factor': 2 };");
        }
        try (final Session session = connect()) {
            session.execute(tableDdl);
        }
    }
