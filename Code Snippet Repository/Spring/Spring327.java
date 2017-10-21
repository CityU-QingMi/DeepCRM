	@Test
	public void testLowerAdvisorPrecedenceAfterAdvice() {
		Advisor advisor1 = createAspectJAfterAdvice(LOW_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someAspect");
		Advisor advisor2 = createAspectJAroundAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor1 sorted after advisor2", 1, this.comparator.compare(advisor1, advisor2));

		advisor1 = createSpringAOPAfterAdvice(LOW_PRECEDENCE_ADVISOR_ORDER);
		advisor2 = createAspectJAfterThrowingAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("advisor1 sorted after advisor2", 1, this.comparator.compare(advisor1, advisor2));
	}
