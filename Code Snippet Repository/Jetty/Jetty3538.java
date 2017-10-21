    protected void check(String response, String... content)
    {
        Assert.assertThat(response, Matchers.startsWith("HTTP/1.1 200 OK"));
        int i = 0;
        for (String m : content)
        {
            Assert.assertThat(response, Matchers.containsString(m));
            i = response.indexOf(m, i);
            i += m.length();
        }
    }
