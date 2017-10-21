    public void testStrutsTokenIsExcluded() throws Exception {
        // given
        List<String> tokens = new ArrayList<>();
        tokens.add("struts.token.name");
        tokens.add("struts.token");

        ExcludedPatternsChecker checker = new DefaultExcludedPatternsChecker();

        for (String token : tokens) {
            // when
            ExcludedPatternsChecker.IsExcluded actual = checker.isExcluded(token);

            // then
            assertTrue("Param '" + token + "' is not excluded!", actual.isExcluded());
        }
    }
