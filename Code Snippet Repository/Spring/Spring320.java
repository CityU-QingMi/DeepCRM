	@Test
	public void testSameAspectNoAfterAdvice() {
		Advisor advisor1 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someAspect");
		Advisor advisor2 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someAspect");
		assertEquals("advisor1 sorted before advisor2", -1, this.comparator.compare(advisor1, advisor2));

		advisor1 = createAspectJBeforeAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, LATE_ADVICE_DECLARATION_ORDER, "someAspect");
		advisor2 = createAspectJAroundAdvice(HIGH_PRECEDENCE_ADVISOR_ORDER, EARLY_ADVICE_DECLARATION_ORDER, "someAspect");
		assertEquals("advisor2 sorted before advisor1", 1, this.comparator.compare(advisor1, advisor2));
	}
