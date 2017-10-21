    @Test
    public void testClassFileTranslations() throws Exception
    {
        final List<Object> results=new ArrayList<Object>();
        
        _loader.addTransformer(new ClassFileTransformer()
        {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException
            {
                results.add(loader);
                byte[] b = new byte[classfileBuffer.length];
                for (int i=0;i<classfileBuffer.length;i++)
                    b[i]=(byte)(classfileBuffer[i]^0xff);
                return b;
            }
        });
        _loader.addTransformer(new ClassFileTransformer()
        {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException
            {
                results.add(className);
                byte[] b = new byte[classfileBuffer.length];
                for (int i=0;i<classfileBuffer.length;i++)
                    b[i]=(byte)(classfileBuffer[i]^0xff);
                return b;
            }
        });
        
        _context.setParentLoaderPriority(false);
        
        assertCanLoadClass("org.acme.webapp.ClassInJarA");
        assertCanLoadClass("org.acme.webapp.ClassInJarB");
        assertCanLoadClass("org.acme.other.ClassInClassesC");
        assertCanLoadClass("java.lang.String");
        assertCantLoadClass("org.eclipse.jetty.webapp.Configuration");
        
        assertThat("Classname Results", results, contains(
                _loader,
                "org.acme.webapp.ClassInJarA",
                _loader,
                "org.acme.webapp.ClassInJarB",
                _loader,
                "org.acme.other.ClassInClassesC"));
    }
