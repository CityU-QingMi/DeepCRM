    @Test
    public void testWebServletAnnotationIgnore () throws Exception
    {
        //an existing servlet OF THE SAME NAME has even 1 non-default mapping we can't use
        //any of the url mappings in the annotation
        WebAppContext wac = new WebAppContext();
        ServletHolder servlet = new ServletHolder();
        servlet.setClassName("org.eclipse.jetty.servlet.OtherDServlet");
        servlet.setName("DServlet");
        wac.getServletHandler().addServlet(servlet);

        ServletMapping m = new ServletMapping();
        m.setPathSpec("/default");
        m.setDefault(true);
        m.setServletName("DServlet");
        wac.getServletHandler().addServletMapping(m);

        ServletMapping m2 = new ServletMapping();
        m2.setPathSpec("/other");
        m2.setServletName("DServlet");
        wac.getServletHandler().addServletMapping(m2);

        WebServletAnnotation annotation = new WebServletAnnotation(wac, "org.eclipse.jetty.annotations.ServletD", null);
        annotation.apply();

        ServletMapping[] resultMappings = wac.getServletHandler().getServletMappings();
        assertEquals(2, resultMappings.length);

        for (ServletMapping r:resultMappings)
        {
            assertEquals(1, r.getPathSpecs().length);
            if (!r.getPathSpecs()[0].equals("/default") && !r.getPathSpecs()[0].equals("/other"))
                fail("Unexpected path in mapping");
        }

    }
