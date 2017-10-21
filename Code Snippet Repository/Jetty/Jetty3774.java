    @Test
    public void testLongHeader() throws Exception
    {
        char[] chars = new char[10000];
        Arrays.fill(chars,'o');
        String ooo = new String(chars);
        _connector.getResponse("METHOD /foo HTTP/1.0\name: f+"+ooo+"\n\n");
        String log = _log.exchange(null,5,TimeUnit.SECONDS);
        assertThat(log,containsString("\"METHOD /foo HTTP/1.0\""));
        assertThat(log,containsString(" 431 0 "));
    }
