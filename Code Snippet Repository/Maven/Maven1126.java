    public void testSetSession() throws Exception {

        MavenExecutionRequest mavenExecutionRequest = new DefaultMavenExecutionRequest();
        MavenSession m1 = new MavenSession(null, null, mavenExecutionRequest, null);
        defaultLegacySupport.setSession(m1);

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        MavenSession m2 = new MavenSession(null, null, mavenExecutionRequest, null);
        defaultLegacySupport.setSession(m2);
        latch.countDown();
        thread.join();
        assertNull( myRunnable.getSession());
    }
