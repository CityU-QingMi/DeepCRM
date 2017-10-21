    @Test
    public void testHeaderFooterThreadContext() throws Exception {
        final PatternLayout layout = PatternLayout.newBuilder().withPattern("%d{UNIX} %m")
                .withConfiguration(ctx.getConfiguration()).withHeader("${ctx:header}").withFooter("${ctx:footer}")
                .build();
        ThreadContext.put("header", "Hello world Header");
        ThreadContext.put("footer", "Hello world Footer");
        final byte[] header = layout.getHeader();
        assertNotNull("No header", header);
        assertTrue("expected \"Hello world Header\", actual " + Strings.dquote(new String(header)),
                new String(header).equals(new String("Hello world Header")));
    }
