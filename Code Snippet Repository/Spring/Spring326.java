	@Test
	public void testLowerAdvisorPrecedenceNoAfterAdvice() {
		Advisor advisor1 = createAspectJBeforeAdvice(LOW_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someAspect");
		Advisor advisor2 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor1 sorted after advisor2", 1, this.comparator.compare(advisor1, advisor2));

		advisor1 = createAspectJBeforeAdvice(LOW_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someAspect");
		advisor2 = createAspectJAroundAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor1 sorted after advisor2", 1, this.comparator.compare(advisor1, advisor2));
	}
