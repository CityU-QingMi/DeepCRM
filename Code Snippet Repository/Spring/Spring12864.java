	@Parameters(name = "")
	public static Object[][] handlerTypes() {
		return new Object[][] {

			{ SimpleController.class, true }, // CGLib proxy
			{ SimpleController.class, false },

			{ AbstractClassController.class, true }, // CGLib proxy
			{ AbstractClassController.class, false },

			{ ParameterizedAbstractClassController.class, true }, // CGLib proxy
			{ ParameterizedAbstractClassController.class, false },

			{ ParameterizedSubclassOverridesDefaultMappings.class, true }, // CGLib proxy
			{ ParameterizedSubclassOverridesDefaultMappings.class, false },

			// TODO [SPR-9517] Enable ParameterizedSubclassDoesNotOverrideConcreteImplementationsFromGenericAbstractSuperclass test cases
			// { ParameterizedSubclassDoesNotOverrideConcreteImplementationsFromGenericAbstractSuperclass.class, true }, // CGLib proxy
			// { ParameterizedSubclassDoesNotOverrideConcreteImplementationsFromGenericAbstractSuperclass.class, false },

			{ InterfaceController.class, true }, // JDK dynamic proxy
			{ InterfaceController.class, false },

			{ ParameterizedInterfaceController.class, false }, // no AOP

			{ SupportClassController.class, true }, // CGLib proxy
			{ SupportClassController.class, false }

		};
	}
