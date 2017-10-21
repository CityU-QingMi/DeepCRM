    @Test
    public void testUnloadableClassName() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(BaseHolder.class, ServletHandler.class, ContextHandler.class, ServletContextHandler.class))
        {
            ServletContextHandler context = new ServletContextHandler(); 
            ServletHandler handler = context.getServletHandler();
            ServletHolder holder = new ServletHolder();
            holder.setName("foo");
            holder.setClassName("a.b.c.class");
            handler.addServlet(holder);
            handler.start();
            Assert.fail("Unloadable class");
        }
        catch (UnavailableException e)
        {
            Assert.assertTrue(e.getMessage().contains("foo"));
        }
        catch (MultiException e)
        {
            MultiException m = (MultiException)e;
            Assert.assertTrue(m.getCause().getMessage().contains("foo"));
        }
    }
