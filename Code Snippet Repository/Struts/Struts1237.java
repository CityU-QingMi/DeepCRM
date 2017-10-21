    public void testApplyMethodWithWildcards() {

        HashSet<String> included = new HashSet<>();
        included.add("included*");

        HashSet<String> excluded = new HashSet<>();
        excluded.add("excluded*");
        
        assertTrue(MethodFilterInterceptorUtil.applyMethod(excluded, included, "includedMethod"));
        assertFalse(MethodFilterInterceptorUtil.applyMethod(excluded, included, "excludedMethod"));

        // test precedence
        included.clear();
        excluded.clear();
        included.add("wildIncluded");
        excluded.add("wild*");
        
        assertTrue(MethodFilterInterceptorUtil.applyMethod(excluded, included, "wildIncluded"));
        assertFalse(MethodFilterInterceptorUtil.applyMethod(excluded, included, "wildNotIncluded"));

        // test precedence
        included.clear();
        excluded.clear();
        included.add("*");
        excluded.add("excluded");

        assertTrue(MethodFilterInterceptorUtil.applyMethod(excluded, included, "anyMethod"));

        // test precedence
        included.clear();
        excluded.clear();
        included.add("included");
        excluded.add("*");

        assertTrue(MethodFilterInterceptorUtil.applyMethod(excluded, included, "included"));
        assertFalse(MethodFilterInterceptorUtil.applyMethod(excluded, included, "shouldBeExcluded"));

    }
