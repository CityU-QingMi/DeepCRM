    public void testApplyMethodNoWildcards() {

        HashSet<String> included = new HashSet<>();
        included.add("included");
        included.add("includedAgain");

        HashSet<String> excluded = new HashSet<>();
        excluded.add("excluded");
        excluded.add("excludedAgain");
        
        // test expected behavior
        assertFalse(MethodFilterInterceptorUtil.applyMethod(excluded, included, "excluded"));
        assertTrue(MethodFilterInterceptorUtil.applyMethod(excluded, included, "included"));

        // test precedence
        included.add("excluded");
        assertTrue(MethodFilterInterceptorUtil.applyMethod(excluded, included, "excluded"));

    }
