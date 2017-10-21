    @Test
    public void testLongURI() throws Exception
    {
        char[] chars = new char[10000];
        Arrays.fill(chars,'o');
        String ooo = new String(chars);
        _connector.getResponse("METHOD /f"+ooo+" HTTP/1.0\n\n");
        String log = _log.exchange(null,5,TimeUnit.SECONDS);
        assertThat(log,containsString("\"- - -\""));
        assertThat(log,containsString(" 414 0 "));
    }
