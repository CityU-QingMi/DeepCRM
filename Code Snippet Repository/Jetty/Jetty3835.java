    @Test
    public void testSNIConnect() throws Exception
    {
        String response = getResponse("jetty.eclipse.org", "jetty.eclipse.org");
        Assert.assertThat(response, Matchers.containsString("X-HOST: jetty.eclipse.org"));

        response = getResponse("www.example.com", "www.example.com");
        Assert.assertThat(response, Matchers.containsString("X-HOST: www.example.com"));

        response = getResponse("foo.domain.com", "*.domain.com");
        Assert.assertThat(response, Matchers.containsString("X-HOST: foo.domain.com"));

        response = getResponse("m.san.com", "san example");
        Assert.assertThat(response, Matchers.containsString("X-HOST: m.san.com"));

        response = getResponse("www.san.com", "san example");
        Assert.assertThat(response, Matchers.containsString("X-HOST: www.san.com"));
    }
