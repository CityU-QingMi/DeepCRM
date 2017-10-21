    @Test
    public void testAsyncNotSupportedAsync() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            _expectedCode="500 ";
            String response=process("noasync","start=200",null);
            Assert.assertThat(response,Matchers.startsWith("HTTP/1.1 500 "));
            assertThat(__history,contains(
                    "REQUEST /ctx/noasync/info",
                    "initial",
                    "ERROR /ctx/error/custom",
                    "!initial"
                    ));

            assertContains("500",response);
            assertContains("!asyncSupported",response);
            assertContains("AsyncServletTest$AsyncServlet",response);
        }
    }
