    @Test
    public void testParseReader() throws Exception
    {
        Map map = (Map)JSON.parse(new StringReader(test));

        assertEquals(new Long(100),map.get("onehundred"));
        assertEquals("fred",map.get("name"));
        assertTrue(map.get("array").getClass().isArray());
        assertTrue(map.get("w0") instanceof Woggle);
        assertTrue(((Woggle)map.get("w0")).nested instanceof Woggle);

        test="{\"data\":{\"source\":\"15831407eqdaawf7\",\"widgetId\":\"Magnet_8\"},\"channel\":\"/magnets/moveStart\",\"connectionId\":null,\"clientId\":\"15831407eqdaawf7\"}";
        map = (Map)JSON.parse(test);
    }
