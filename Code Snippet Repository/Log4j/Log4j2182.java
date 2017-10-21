    @Test
    public void testLogStackTraceWithClassThatWillCauseSecurityException() throws IOException {
        final SecurityManager sm = System.getSecurityManager();
        try {
            System.setSecurityManager(
                    new SecurityManager() {
                        @Override
                        public void checkPermission(final Permission perm) {
                            if (perm instanceof RuntimePermission) {
                                // deny access to the class to trigger the security exception
                                if ("accessClassInPackage.sun.nio.ch".equals(perm.getName())) {
                                    throw new SecurityException(perm.toString());
                                }
                            }
                        }
                    });
            ServerSocketChannel.open().socket().bind(new InetSocketAddress("localhost", 9300));
            ServerSocketChannel.open().socket().bind(new InetSocketAddress("localhost", 9300));
            fail("expected a java.net.BindException");
        } catch (final BindException e) {
            new ThrowableProxy(e);
        } finally {
            // restore the security manager
            System.setSecurityManager(sm);
        }
    }
