    public void testUnderscoreInParamName() throws Exception {
        // given
        AcceptedPatternsChecker checker = new DefaultAcceptedPatternsChecker();

        // when
        AcceptedPatternsChecker.IsAccepted actual = checker.isAccepted("mapParam['param_1']");

        // then
        assertTrue("Param with underscore wasn't accepted!", actual.isAccepted());
    }
