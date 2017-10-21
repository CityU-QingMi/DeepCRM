    @Test
    public void testMissingResourceDeclaration()
    throws Exception
    {
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(context.getClassLoader());

        try
        {
            PlusDescriptorProcessor pdp = new PlusDescriptorProcessor();
            pdp.process(context, fragDescriptor4);
            fail("Expected missing resource declaration");
        }
        catch (InvocationTargetException ex)
        {
            Throwable cause = ex.getCause();
            assertNotNull(cause);
            assertNotNull(cause.getMessage());
            assertTrue(cause.getMessage().contains("jdbc/mymissingdatasource"));
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(oldLoader);
        }
    }
