	private InjectionMetadata buildPersistenceMetadata(final Class<?> clazz) {
		LinkedList<InjectionMetadata.InjectedElement> elements = new LinkedList<>();
		Class<?> targetClass = clazz;

		do {
			final LinkedList<InjectionMetadata.InjectedElement> currElements =
					new LinkedList<>();

			ReflectionUtils.doWithLocalFields(targetClass, field -> {
				if (field.isAnnotationPresent(PersistenceContext.class) ||
						field.isAnnotationPresent(PersistenceUnit.class)) {
					if (Modifier.isStatic(field.getModifiers())) {
						throw new IllegalStateException("Persistence annotations are not supported on static fields");
					}
					currElements.add(new PersistenceElement(field, field, null));
				}
			});

			ReflectionUtils.doWithLocalMethods(targetClass, method -> {
				Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
				if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
					return;
				}
				if ((bridgedMethod.isAnnotationPresent(PersistenceContext.class) ||
						bridgedMethod.isAnnotationPresent(PersistenceUnit.class)) &&
						method.equals(ClassUtils.getMostSpecificMethod(method, clazz))) {
					if (Modifier.isStatic(method.getModifiers())) {
						throw new IllegalStateException("Persistence annotations are not supported on static methods");
					}
					if (method.getParameterCount() != 1) {
						throw new IllegalStateException("Persistence annotation requires a single-arg method: " + method);
					}
					PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, clazz);
					currElements.add(new PersistenceElement(method, bridgedMethod, pd));
				}
			});

			elements.addAll(0, currElements);
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null && targetClass != Object.class);

		return new InjectionMetadata(clazz, elements);
	}
