    @Test
    public void testWildSNIConnect() throws Exception
    {
        String response = getResponse("domain.com", "www.domain.com", "*.domain.com");
        Assert.assertThat(response, Matchers.containsString("X-HOST: www.domain.com"));

        response = getResponse("domain.com", "domain.com", "*.domain.com");
        Assert.assertThat(response, Matchers.containsString("X-HOST: domain.com"));

        response = getResponse("www.domain.com", "www.domain.com", "*.domain.com");
        Assert.assertThat(response, Matchers.containsString("X-HOST: www.domain.com"));
    }
