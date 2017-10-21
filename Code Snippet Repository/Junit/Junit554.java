	private int invokeAppropriateMethodOnRuleAnnotatedMembers(ExtensionContext context, Consumer<List<Member>> ordering,
			Consumer<GenericBeforeAndAfterAdvice> methodCaller) {

		Object testInstance = context.getRequiredTestInstance();
		List<Member> members = findRuleAnnotatedMembers(testInstance);
		ordering.accept(members);

		// @formatter:off
		members.stream()
				.map(member -> TestRuleAnnotatedMemberFactory.from(testInstance, member))
				.map(this.adapterGenerator)
				.forEach(methodCaller::accept);
		// @formatter:on

		return members.size();
	}
