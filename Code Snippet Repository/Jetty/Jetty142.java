    @Test
    public void testWebServletAnnotationNotOverride () throws Exception
    {
        //if the existing servlet mapping TO A DIFFERENT SERVLET IS NOT from a default descriptor we
        //DO NOT allow the annotation to replace the mapping
        WebAppContext wac = new WebAppContext();
        ServletHolder servlet = new ServletHolder();
        servlet.setClassName("org.eclipse.jetty.servlet.FooServlet");
        servlet.setName("foo");
        wac.getServletHandler().addServlet(servlet);
        ServletMapping m = new ServletMapping();
        m.setPathSpec("/");
        m.setServletName("foo");
        wac.getServletHandler().addServletMapping(m);

        WebServletAnnotation annotation = new WebServletAnnotation(wac, "org.eclipse.jetty.annotations.ServletD", null);
        annotation.apply();

        ServletMapping[] resultMappings = wac.getServletHandler().getServletMappings();
        assertEquals(2, resultMappings.length);
        for (ServletMapping r:resultMappings)
        {
            if (r.getServletName().equals("DServlet"))
            {
                assertEquals(2, r.getPathSpecs().length);
            }
            else if (r.getServletName().equals("foo"))
            {
                assertEquals(1, r.getPathSpecs().length);
            }
            else
                fail("Unexpected servlet name");
        }
    }
