    private void assertProcessedByJspServlet(HttpURLConnection conn) throws IOException
    {
        // make sure that jsp actually ran, and didn't just get passed onto
        // the default servlet to return the jsp source
        String body = getResponseBody(conn);

        if (knownBypass && body.indexOf("<%@")>=0)
            LOG.info("Known bypass of mapping by "+path);
        else
        {
            Assert.assertThat("Body",body,not(containsString("<%@")));
            Assert.assertThat("Body",body,not(containsString("<jsp:")));
        }
    }
