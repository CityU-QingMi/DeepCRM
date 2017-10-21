    @Test
    public void testNoClassName() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(ServletHandler.class, ContextHandler.class, ServletContextHandler.class))
        {
            ServletContextHandler context = new ServletContextHandler(); 
            ServletHandler handler = context.getServletHandler();
            ServletHolder holder = new ServletHolder();
            holder.setName("foo");
            holder.setForcedPath("/blah/");
            handler.addServlet(holder);
            handler.start();
            Assert.fail("No class in ServletHolder");
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
