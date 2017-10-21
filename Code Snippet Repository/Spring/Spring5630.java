	public static List<PropertyAccessor> getPropertyAccessorsToTry(
			@Nullable Class<?> targetType, List<PropertyAccessor> propertyAccessors) {

		List<PropertyAccessor> specificAccessors = new ArrayList<>();
		List<PropertyAccessor> generalAccessors = new ArrayList<>();
		for (PropertyAccessor resolver : propertyAccessors) {
			Class<?>[] targets = resolver.getSpecificTargetClasses();
			if (targets == null) {  // generic resolver that says it can be used for any type
				generalAccessors.add(resolver);
			}
			else {
				if (targetType != null) {
					int pos = 0;
					for (Class<?> clazz : targets) {
						if (clazz == targetType) {  // put exact matches on the front to be tried first?
							specificAccessors.add(pos++, resolver);
						}
						else if (clazz.isAssignableFrom(targetType)) {  // put supertype matches at the end of the
							// specificAccessor list
							generalAccessors.add(resolver);
						}
					}
				}
			}
		}
		List<PropertyAccessor> resolvers = new LinkedList<>();
		resolvers.addAll(specificAccessors);
		resolvers.addAll(generalAccessors);
		return resolvers;
	}
