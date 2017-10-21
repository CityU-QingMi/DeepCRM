    @Test
    public void testWebServletAnnotationOverrideDefault () throws Exception
    {
        //if the existing servlet mapping TO A DIFFERENT SERVLET IS from a default descriptor we
        //DO allow the annotation to replace the mapping.

        WebAppContext wac = new WebAppContext();
        ServletHolder defaultServlet = new ServletHolder();
        defaultServlet.setClassName("org.eclipse.jetty.servlet.DefaultServlet");
        defaultServlet.setName("default");
        wac.getServletHandler().addServlet(defaultServlet);

        ServletMapping m = new ServletMapping();
        m.setPathSpec("/");
        m.setServletName("default");
        m.setDefault(true);  //this mapping will be from a default descriptor
        wac.getServletHandler().addServletMapping(m);

        WebServletAnnotation annotation = new WebServletAnnotation(wac, "org.eclipse.jetty.annotations.ServletD", null);
        annotation.apply();

        //test that as the original servlet mapping had only 1 pathspec, then the whole
        //servlet mapping should be deleted as that pathspec will be remapped to the DServlet
        ServletMapping[] resultMappings = wac.getServletHandler().getServletMappings();
        assertNotNull(resultMappings);
        assertEquals(1, resultMappings.length);
        assertEquals(2, resultMappings[0].getPathSpecs().length);
        resultMappings[0].getServletName().equals("DServlet");
        for (String s:resultMappings[0].getPathSpecs())
        {
            assertTrue (s.equals("/") || s.equals("/bah/*"));
        }
    }
