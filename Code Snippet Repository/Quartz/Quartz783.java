	public void testAnyGroupMatchers() {

        TriggerKey tKey = triggerKey("booboo", "baz");
        JobKey jKey = jobKey("frumpwomp", "bazoo");

        GroupMatcher tgm = anyTriggerGroup();
        GroupMatcher jgm = anyJobGroup();

        Assert.assertTrue("Expected match on trigger group", tgm.isMatch(tKey));
        Assert.assertTrue("Expected match on job group", jgm.isMatch(jKey));

	}
