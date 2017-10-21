	@Test
	public void testHigherAdvisorPrecedenceAfterAdvice() {
		Advisor advisor1 = createAspectJAfterAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someAspect");
		Advisor advisor2 = createAspectJAroundAdvice(LOW_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor1 sorted before advisor2", -1, this.comparator.compare(advisor1, advisor2));

		advisor1 = createAspectJAfterReturningAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someAspect");
		advisor2 = createAspectJAfterThrowingAdvice(LOW_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor2 sorted after advisor1", -1, this.comparator.compare(advisor1, advisor2));
	}
