    @Test
    public void testServletAnnotation() throws Exception
    {
        List<String> classes = new ArrayList<String>();
        classes.add("org.eclipse.jetty.annotations.ServletC");
        AnnotationParser parser = new AnnotationParser();

        WebAppContext wac = new WebAppContext();
        List<DiscoveredAnnotation> results = new ArrayList<DiscoveredAnnotation>();

        TestWebServletAnnotationHandler handler = new TestWebServletAnnotationHandler(wac, results);

        parser.parse(Collections.singleton(handler), classes);


        assertEquals(1, results.size());
        assertTrue(results.get(0) instanceof WebServletAnnotation);

        results.get(0).apply();

        ServletHolder[] holders = wac.getServletHandler().getServlets();
        assertNotNull(holders);
        assertEquals(1, holders.length);

        // Verify servlet annotations
        ServletHolder cholder = holders[0];
        assertThat("Servlet Name", cholder.getName(), is("CServlet"));
        assertThat("InitParameter[x]", cholder.getInitParameter("x"), is("y"));
        assertThat("Init Order", cholder.getInitOrder(), is(2));
        assertThat("Async Supported", cholder.isAsyncSupported(), is(false));

        // Verify mappings
        ServletMapping[] mappings = wac.getServletHandler().getServletMappings();
        assertNotNull(mappings);
        assertEquals(1, mappings.length);
        String[] paths = mappings[0].getPathSpecs();
        assertNotNull(paths);
        assertEquals(2, paths.length);
    }
