	@Test
	public void testSameAdvisorPrecedenceDifferentAspectNoAfterAdvice() {
		Advisor advisor1 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someAspect");
		Advisor advisor2 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("nothing to say about order here", 0, this.comparator.compare(advisor1, advisor2));

		advisor1 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someAspect");
		advisor2 = createAspectJAroundAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someOtherAspect");
		assertEquals("nothing to say about order here", 0, this.comparator.compare(advisor1, advisor2));
	}
