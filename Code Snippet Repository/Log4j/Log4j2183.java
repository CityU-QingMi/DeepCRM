    @Test
    public void testLogStackTraceWithClassLoaderThatWithCauseSecurityException() throws Exception {
        final SecurityManager sm = System.getSecurityManager();
        try {
            System.setSecurityManager(
                    new SecurityManager() {
                        @Override
                        public void checkPermission(final Permission perm) {
                            if (perm instanceof RuntimePermission) {
                                // deny access to the classloader to trigger the security exception
                                if ("getClassLoader".equals(perm.getName())) {
                                    throw new SecurityException(perm.toString());
                                }
                            }
                        }
                    });
            final String algorithm = "AES/CBC/PKCS5Padding";
            final Cipher ec = Cipher.getInstance(algorithm);
            final byte[] bytes = new byte[16]; // initialization vector
            final SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(bytes);
            final KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128);
            final IvParameterSpec algorithmParameterSpec = new IvParameterSpec(bytes);
            ec.init(Cipher.ENCRYPT_MODE, generator.generateKey(), algorithmParameterSpec, secureRandom);
            final byte[] raw = new byte[0];
            final byte[] encrypted = ec.doFinal(raw);
            final Cipher dc = Cipher.getInstance(algorithm);
            dc.init(Cipher.DECRYPT_MODE, generator.generateKey(), algorithmParameterSpec, secureRandom);
            dc.doFinal(encrypted);
            fail("expected a javax.crypto.BadPaddingException");
        } catch (final BadPaddingException e) {
            new ThrowableProxy(e);
        } finally {
            // restore the existing security manager
            System.setSecurityManager(sm);
        }
    }
