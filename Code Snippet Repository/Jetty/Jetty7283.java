    @Test
    public void testNormal() throws Exception
    {
        String response = process(null, null);
        assertThat(response, startsWith("HTTP/1.1 200 OK"));
        assertThat(response, containsString("NORMAL"));
        assertThat(history, hasItem(expectedImplClass.getName()));
        assertThat(history, not(hasItem("onTimeout")));
        assertThat(history, not(hasItem("onComplete")));
    }
