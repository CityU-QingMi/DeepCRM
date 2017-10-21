    @Test
    public void testToStringShowsMapContext() {
        final DefaultThreadContextMap map = new DefaultThreadContextMap(true);
        assertEquals("{}", map.toString());

        map.put("key1", "value1");
        assertEquals("{key1=value1}", map.toString());

        map.remove("key1");
        map.put("key2", "value2");
        assertEquals("{key2=value2}", map.toString());
    }
