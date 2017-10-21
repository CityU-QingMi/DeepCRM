    @Test
    public void testMatchingFragmentResourceDeclarations ()
    throws Exception
    {
        //if declared in more than 1 fragment, declarations must be the same
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(context.getClassLoader());
        try
        {
            PlusDescriptorProcessor pdp = new PlusDescriptorProcessor();
            pdp.process(context, fragDescriptor1);
            Descriptor d = context.getMetaData().getOriginDescriptor("resource-ref.jdbc/mydatasource");
            assertNotNull(d);
            assertTrue(d == fragDescriptor1);
            assertEquals(Origin.WebFragment, context.getMetaData().getOrigin("resource-ref.jdbc/mydatasource"));
            pdp.process(context, fragDescriptor3);
        }

        finally
        {
            Thread.currentThread().setContextClassLoader(oldLoader);
        }
    }
