    @Test
    public void testWebXmlResourceDeclarations()
    throws Exception
    {
        //if declared in web.xml, fragment declarations ignored
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(context.getClassLoader());
        try
        {
            PlusDescriptorProcessor pdp = new PlusDescriptorProcessor();
            pdp.process(context, webDescriptor);
            Descriptor d = context.getMetaData().getOriginDescriptor("resource-ref.jdbc/mydatasource");
            assertNotNull(d);
            assertTrue(d == webDescriptor);

            pdp.process(context, fragDescriptor1);
            pdp.process(context, fragDescriptor2);
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(oldLoader);
        }
    }
