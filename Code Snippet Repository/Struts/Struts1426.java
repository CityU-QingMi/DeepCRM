    public void testParamWithClassInName() throws Exception {
        // given
        List<String> properParams = new ArrayList<>();
        properParams.add("eventClass");
        properParams.add("form.eventClass");
        properParams.add("form[\"eventClass\"]");
        properParams.add("form['eventClass']");
        properParams.add("class.super@demo.com");
        properParams.add("super.class@demo.com");

        ExcludedPatternsChecker checker = new DefaultExcludedPatternsChecker();

        for (String properParam : properParams) {
            // when
            ExcludedPatternsChecker.IsExcluded actual = checker.isExcluded(properParam);

            // then
            assertFalse("Param '" + properParam + "' is excluded!", actual.isExcluded());
        }
    }
