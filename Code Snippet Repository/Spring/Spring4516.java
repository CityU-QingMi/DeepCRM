	@Nullable
	private static Member getValidatedMember(Class<?> targetClass, Class<?> sourceClass) {
		Member member = conversionMemberCache.get(targetClass);
		if (isApplicable(member, sourceClass)) {
			return member;
		}

		member = determineToMethod(targetClass, sourceClass);
		if (member == null) {
			member = determineFactoryMethod(targetClass, sourceClass);
			if (member == null) {
				member = determineFactoryConstructor(targetClass, sourceClass);
				if (member == null) {
					return null;
				}
			}
		}

		conversionMemberCache.put(targetClass, member);
		return member;
	}
