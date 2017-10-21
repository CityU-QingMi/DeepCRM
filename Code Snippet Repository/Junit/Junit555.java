	public static TestRuleAnnotatedMember from(Object testInstance, Member member) {
		if (member instanceof Method) {
			return new TestRuleAnnotatedMethod(testInstance, (Method) member);
		}
		if (member instanceof Field) {
			return new TestRuleAnnotatedField(testInstance, (Field) member);
		}
		throw new PreconditionViolationException(
			String.format("Unsupported Member type [%s] for TestRule. Member must be of type %s or %s", member,
				Method.class.getName(), Field.class.getName()));
	}
