    @Test
    public void testLookup() {
        final Map<String, String> map = new HashMap<>();
        map.put(TESTKEY, TESTVAL);
        final StrLookup lookup = new Interpolator(new MapLookup(map));
        final StrSubstitutor subst = new StrSubstitutor(lookup);
        ThreadContext.put(TESTKEY, TESTVAL);
        String value = subst.replace("${TestKey}-${ctx:TestKey}-${sys:TestKey}");
        assertEquals("TestValue-TestValue-TestValue", value);
        value = subst.replace("${BadKey}");
        assertEquals("${BadKey}", value);

        value = subst.replace("${BadKey:-Unknown}-${ctx:BadKey:-Unknown}-${sys:BadKey:-Unknown}");
        assertEquals("Unknown-Unknown-Unknown", value);
        value = subst.replace("${BadKey:-Unknown}-${ctx:BadKey}-${sys:BadKey:-Unknown}");
        assertEquals("Unknown-${ctx:BadKey}-Unknown", value);
        value = subst.replace("${BadKey:-Unknown}-${ctx:BadKey:-}-${sys:BadKey:-Unknown}");
        assertEquals("Unknown--Unknown", value);
    }
