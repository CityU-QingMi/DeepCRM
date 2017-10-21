	private void addEntityInstanceHandling(CtClass managedCtClass) {
		try {
			MethodWriter.write(
					managedCtClass,
					"public Object %s() { return this; }",
					EnhancerConstants.ENTITY_INSTANCE_GETTER_NAME
			);
		}
		catch (CannotCompileException cce) {
			throw new EnhancementException(
					String.format(
							Locale.ROOT,
							"Could not enhance entity class [%s] to add EntityEntry getter",
							managedCtClass.getName()
					),
					cce
			);
		}
	}
