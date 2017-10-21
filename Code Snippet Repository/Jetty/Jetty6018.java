    @Test
    public void testNullClassFileTransformer () throws Exception
    {
        _loader.addTransformer(new ClassFileTransformer()
        {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException
            {
                return null;
            }
        });
        
        assertCanLoadClass("org.acme.webapp.ClassInJarA");
    }
