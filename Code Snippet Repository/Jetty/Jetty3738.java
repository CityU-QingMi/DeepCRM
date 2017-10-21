    @Test
    public void testAttributes() throws Exception
    {
        ContextHandler handler = new ContextHandler();
        handler.setServer(new Server());
        handler.setAttribute("aaa","111");
        Assert.assertEquals("111", handler.getServletContext().getAttribute("aaa"));
        Assert.assertEquals(null, handler.getAttribute("bbb"));

        handler.start();

        handler.getServletContext().setAttribute("aaa","000");
        handler.setAttribute("ccc","333");
        handler.getServletContext().setAttribute("ddd","444");
        Assert.assertEquals("111", handler.getServletContext().getAttribute("aaa"));
        Assert.assertEquals(null, handler.getServletContext().getAttribute("bbb"));
        handler.getServletContext().setAttribute("bbb","222");
        Assert.assertEquals("333", handler.getServletContext().getAttribute("ccc"));
        Assert.assertEquals("444", handler.getServletContext().getAttribute("ddd"));

        Assert.assertEquals("111", handler.getAttribute("aaa"));
        Assert.assertEquals(null, handler.getAttribute("bbb"));
        Assert.assertEquals("333", handler.getAttribute("ccc"));
        Assert.assertEquals(null, handler.getAttribute("ddd"));

        handler.stop();

        Assert.assertEquals("111", handler.getServletContext().getAttribute("aaa"));
        Assert.assertEquals(null, handler.getServletContext().getAttribute("bbb"));
        Assert.assertEquals("333", handler.getServletContext().getAttribute("ccc"));
        Assert.assertEquals(null, handler.getServletContext().getAttribute("ddd"));
    }
