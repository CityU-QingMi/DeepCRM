    @Test
    public void testTransitiveCompareTo() throws Exception
    {
        // example of jsp-file referenced in web.xml
        final ServletHolder one = new ServletHolder();
        one.setInitOrder(-1);
        one.setName("Login");
        one.setClassName(null);

        // example of pre-compiled jsp
        final ServletHolder two = new ServletHolder();
        two.setInitOrder(-1);
        two.setName("org.my.package.jsp.WEB_002dINF.pages.precompiled_002dpage_jsp");
        two.setClassName("org.my.package.jsp.WEB_002dINF.pages.precompiled_002dpage_jsp");

        // example of servlet referenced in web.xml
        final ServletHolder three = new ServletHolder();
        three.setInitOrder(-1);
        three.setName("Download");
        three.setClassName("org.my.package.web.DownloadServlet");

        // verify compareTo transitivity
        Assert.assertTrue(one.compareTo(two) < 0);
        Assert.assertTrue(two.compareTo(three) < 0);
        Assert.assertTrue(one.compareTo(three) < 0);
    }
