	@Test
	public void isTestEnabledInThisEnvironmentForProvidedClass() throws Exception {
		assertClassIsEnabled(NonAnnotated.class);
		assertClassIsEnabled(EnabledAnnotatedSingleValue.class);
		assertClassIsEnabled(EnabledAnnotatedMultiValue.class);
		assertClassIsEnabled(MetaEnabledClass.class);
		assertClassIsEnabled(MetaEnabledWithCustomProfileValueSourceClass.class);
		assertClassIsEnabled(EnabledWithCustomProfileValueSourceOnTestInterface.class);

		assertClassIsDisabled(DisabledAnnotatedSingleValue.class);
		assertClassIsDisabled(DisabledAnnotatedSingleValueOnTestInterface.class);
		assertClassIsDisabled(DisabledAnnotatedMultiValue.class);
		assertClassIsDisabled(MetaDisabledClass.class);
		assertClassIsDisabled(MetaDisabledWithCustomProfileValueSourceClass.class);
	}
