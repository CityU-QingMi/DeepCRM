    @Test
    public void testParse()
    {
        Map map = (Map)JSON.parse(test);
        assertEquals(new Long(100),map.get("onehundred"));
        assertEquals("fred",map.get("name"));
        assertEquals(-0.2,map.get("small"));
        assertTrue(map.get("array").getClass().isArray());
        assertTrue(map.get("w0") instanceof Woggle);
        assertTrue(((Woggle)map.get("w0")).nested instanceof Woggle);
        assertEquals(-101,((Woggle)((Woggle)map.get("w0")).nested).number);
        assertTrue(map.containsKey("NaN"));
        assertEquals(null,map.get("NaN"));
        assertTrue(map.containsKey("undefined"));
        assertEquals(null,map.get("undefined"));

        test="{\"data\":{\"source\":\"15831407eqdaawf7\",\"widgetId\":\"Magnet_8\"},\"channel\":\"/magnets/moveStart\",\"connectionId\":null,\"clientId\":\"15831407eqdaawf7\"}";
        map = (Map)JSON.parse(test);
    }
