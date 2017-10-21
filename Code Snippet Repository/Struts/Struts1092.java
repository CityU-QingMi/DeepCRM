    public void testLooseMatch() {
        configMap.put("*!*", configMap.get("bar/*/**"));
        ActionConfigMatcher matcher = new ActionConfigMatcher(new WildcardHelper(), configMap, true);
        
        // exact match
        ActionConfig m = matcher.match("foo/class/method");
        assertNotNull("ActionConfig should be matched", m);
        assertTrue("Class hasn't been replaced "+m.getClassName(), "foo.bar.classAction".equals(m.getClassName()));
        assertTrue("Method hasn't been replaced", "domethod".equals(m.getMethodName()));
        
        // Missing last wildcard
        m = matcher.match("foo/class");
        assertNotNull("ActionConfig should be matched", m);
        assertTrue("Class hasn't been replaced", "foo.bar.classAction".equals(m.getClassName()));
        assertTrue("Method hasn't been replaced, "+m.getMethodName(), "do".equals(m.getMethodName()));
        
        // Simple mapping
        m = matcher.match("class!method");
        assertNotNull("ActionConfig should be matched", m);
        assertTrue("Class hasn't been replaced, "+m.getPackageName(), "package-class".equals(m.getPackageName()));
        assertTrue("Method hasn't been replaced", "method".equals(m.getParams().get("first")));
        
        // Simple mapping
        m = matcher.match("class");
        assertNotNull("ActionConfig should be matched", m);
        assertTrue("Class hasn't been replaced", "package-class".equals(m.getPackageName()));
        assertTrue("Method hasn't been replaced", "".equals(m.getParams().get("first")));
        
    }
