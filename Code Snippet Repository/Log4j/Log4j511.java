    @Test
    public void complexTest() throws Exception {
        File file = new File("target/classes");
        ClassLoader classLoader = new URLClassLoader(new URL[] {file.toURI().toURL()});
        Worker worker = new Worker();
        worker.setContextClassLoader(classLoader);
        worker.start();
        worker.join();
        assertTrue("Incorrect LoggerContext", worker.context instanceof TestLoggerContext);
    }
