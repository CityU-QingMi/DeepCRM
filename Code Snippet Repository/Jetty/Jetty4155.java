    @Test
    public void testGetLowercaseHeader() throws IOException
    {
        HttpURLConnection http = null;
        try
        {
            http = (HttpURLConnection)serverUri.toURL().openConnection();
            // Set header in all lowercase
            http.setRequestProperty("x-camel-type","bactrian");
            
            try (InputStream in = http.getInputStream())
            {
                String resp = IO.toString(in, StandardCharsets.UTF_8);
                Assert.assertThat("Response", resp, is("X-Camel-Type = bactrian"));
            }
        }
        finally
        {
            if (http != null)
            {
                http.disconnect();
            }
        }
    }
