    private void assertResponse(HttpURLConnection conn) throws IOException
    {
        if (conn.getResponseCode() == 200)
        {
            // Serving content is allowed, but it better be the processed JspServlet
            assertProcessedByJspServlet(conn);
            return;
        }

        // Of other possible paths, only 404 Not Found is expected
        Assert.assertThat("Response Code",conn.getResponseCode(),is(404));
    }
