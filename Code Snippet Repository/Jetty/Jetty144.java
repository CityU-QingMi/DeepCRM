    @Test
    public void testWebServletAnnotationNoMappings () throws Exception
    {
        //an existing servlet OF THE SAME NAME has no mappings, therefore all mappings in the annotation
        //should be accepted
        WebAppContext wac = new WebAppContext();
        ServletHolder servlet = new ServletHolder();
        servlet.setName("foo");
        wac.getServletHandler().addServlet(servlet);


        WebServletAnnotation annotation = new WebServletAnnotation(wac, "org.eclipse.jetty.annotations.ServletD", null);
        annotation.apply();

        ServletMapping[] resultMappings = wac.getServletHandler().getServletMappings();
        assertEquals(1, resultMappings.length);
        assertEquals(2, resultMappings[0].getPathSpecs().length);
        for (String s:resultMappings[0].getPathSpecs())
        {
            if (!s.equals("/") && !s.equals("/bah/*"))
                fail("Unexpected path mapping");
        }
    }
