	@Test
	public void testHigherAdvisorPrecedenceNoAfterAdvice() {
		Advisor advisor1 = createSpringAOPBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER);
		Advisor advisor2 = createAspectJBeforeAdvice(LOW_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor1 sorted before advisor2", -1, this.comparator.compare(advisor1, advisor2));

		advisor1 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someAspect");
		advisor2 = createAspectJAroundAdvice(LOW_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor1 sorted before advisor2", -1, this.comparator.compare(advisor1, advisor2));
	}
