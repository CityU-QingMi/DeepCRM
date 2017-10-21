    public static RuleChain create(final TestRule... testRules) {
        if (testRules == null || testRules.length == 0) {
            return RuleChain.emptyRuleChain();
        }
        RuleChain ruleChain = RuleChain.outerRule(testRules[0]);
        for (int i = 1; i < testRules.length; i++) {
            ruleChain = ruleChain.around(testRules[i]);
        }
        return ruleChain;
    }
