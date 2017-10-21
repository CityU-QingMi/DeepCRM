    @Test
    public void testGetRange() throws Exception
    {
        URI uri = serverUri.resolve("range.txt");

        HttpURLConnection uconn = (HttpURLConnection)uri.toURL().openConnection();
        uconn.setRequestMethod("GET");
        uconn.addRequestProperty("Range","bytes=" + 5 + "-");

        int contentLength = Integer.parseInt(uconn.getHeaderField("Content-Length"));

        String response;
        try (InputStream is = uconn.getInputStream())
        {
            response = IO.toString(is);
        }

        Assert.assertThat("Content Length",contentLength,is(5));
        Assert.assertThat("Response Content",response,is("56789"));
    }
