    @Test
    public void testDefault() {
        final Map<String, String> map = new HashMap<>();
        map.put(TESTKEY, TESTVAL);
        final StrLookup lookup = new Interpolator(new MapLookup(map));
        final StrSubstitutor subst = new StrSubstitutor(lookup);
        ThreadContext.put(TESTKEY, TESTVAL);
        //String value = subst.replace("${sys:TestKey1:-${ctx:TestKey}}");
        final String value = subst.replace("${sys:TestKey1:-${ctx:TestKey}}");
        assertEquals("TestValue", value);
    }
