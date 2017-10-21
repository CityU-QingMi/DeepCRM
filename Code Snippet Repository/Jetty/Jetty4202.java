    @Test
    public void testJspFileNameToClassName() throws Exception
    {
        ServletHolder h = new ServletHolder();
        h.setName("test");


        Assert.assertEquals(null,  h.getClassNameForJsp(null));

        Assert.assertEquals(null,  h.getClassNameForJsp(""));

        Assert.assertEquals(null,  h.getClassNameForJsp("/blah/"));

        Assert.assertEquals(null,  h.getClassNameForJsp("//blah///"));

        Assert.assertEquals(null,  h.getClassNameForJsp("/a/b/c/blah/"));

        Assert.assertEquals("org.apache.jsp.a.b.c.blah",  h.getClassNameForJsp("/a/b/c/blah"));

        Assert.assertEquals("org.apache.jsp.blah_jsp", h.getClassNameForJsp("/blah.jsp"));

        Assert.assertEquals("org.apache.jsp.blah_jsp", h.getClassNameForJsp("//blah.jsp"));

        Assert.assertEquals("org.apache.jsp.blah_jsp", h.getClassNameForJsp("blah.jsp"));

        Assert.assertEquals("org.apache.jsp.a.b.c.blah_jsp", h.getClassNameForJsp("/a/b/c/blah.jsp"));

        Assert.assertEquals("org.apache.jsp.a.b.c.blah_jsp", h.getClassNameForJsp("a/b/c/blah.jsp"));
    }
