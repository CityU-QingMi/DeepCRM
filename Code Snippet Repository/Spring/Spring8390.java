	@Test
	public void isTestEnabledInThisEnvironmentForProvidedProfileValueSourceMethodAndClass() throws Exception {

		ProfileValueSource profileValueSource = SystemProfileValueSource.getInstance();

		assertMethodIsEnabled(profileValueSource, NON_ANNOTATED_METHOD, NonAnnotated.class);

		assertMethodIsEnabled(profileValueSource, NON_ANNOTATED_METHOD, EnabledAnnotatedSingleValue.class);
		assertMethodIsEnabled(profileValueSource, ENABLED_ANNOTATED_METHOD, EnabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(profileValueSource, DISABLED_ANNOTATED_METHOD, EnabledAnnotatedSingleValue.class);

		assertMethodIsEnabled(profileValueSource, NON_ANNOTATED_METHOD, EnabledAnnotatedMultiValue.class);
		assertMethodIsEnabled(profileValueSource, ENABLED_ANNOTATED_METHOD, EnabledAnnotatedMultiValue.class);
		assertMethodIsDisabled(profileValueSource, DISABLED_ANNOTATED_METHOD, EnabledAnnotatedMultiValue.class);

		assertMethodIsDisabled(profileValueSource, NON_ANNOTATED_METHOD, DisabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(profileValueSource, ENABLED_ANNOTATED_METHOD, DisabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(profileValueSource, DISABLED_ANNOTATED_METHOD, DisabledAnnotatedSingleValue.class);

		assertMethodIsDisabled(profileValueSource, NON_ANNOTATED_METHOD, DisabledAnnotatedMultiValue.class);
		assertMethodIsDisabled(profileValueSource, ENABLED_ANNOTATED_METHOD, DisabledAnnotatedMultiValue.class);
		assertMethodIsDisabled(profileValueSource, DISABLED_ANNOTATED_METHOD, DisabledAnnotatedMultiValue.class);
	}
