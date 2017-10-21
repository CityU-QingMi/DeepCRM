	@Test
	public void isTestEnabledInThisEnvironmentForProvidedMethodAndClass() throws Exception {
		assertMethodIsEnabled(NON_ANNOTATED_METHOD, NonAnnotated.class);

		assertMethodIsEnabled(NON_ANNOTATED_METHOD, EnabledAnnotatedSingleValue.class);
		assertMethodIsEnabled(ENABLED_ANNOTATED_METHOD, EnabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(DISABLED_ANNOTATED_METHOD, EnabledAnnotatedSingleValue.class);


		assertMethodIsEnabled(NON_ANNOTATED_METHOD, MetaEnabledAnnotatedSingleValue.class);
		assertMethodIsEnabled(ENABLED_ANNOTATED_METHOD, MetaEnabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(DISABLED_ANNOTATED_METHOD, MetaEnabledAnnotatedSingleValue.class);

		assertMethodIsEnabled(NON_ANNOTATED_METHOD, EnabledAnnotatedMultiValue.class);
		assertMethodIsEnabled(ENABLED_ANNOTATED_METHOD, EnabledAnnotatedMultiValue.class);
		assertMethodIsDisabled(DISABLED_ANNOTATED_METHOD, EnabledAnnotatedMultiValue.class);

		assertMethodIsDisabled(NON_ANNOTATED_METHOD, DisabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(ENABLED_ANNOTATED_METHOD, DisabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(DISABLED_ANNOTATED_METHOD, DisabledAnnotatedSingleValue.class);

		assertMethodIsDisabled(NON_ANNOTATED_METHOD, DisabledAnnotatedSingleValueOnTestInterface.class);

		assertMethodIsDisabled(NON_ANNOTATED_METHOD, MetaDisabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(ENABLED_ANNOTATED_METHOD, MetaDisabledAnnotatedSingleValue.class);
		assertMethodIsDisabled(DISABLED_ANNOTATED_METHOD, MetaDisabledAnnotatedSingleValue.class);

		assertMethodIsDisabled(NON_ANNOTATED_METHOD, DisabledAnnotatedMultiValue.class);
		assertMethodIsDisabled(ENABLED_ANNOTATED_METHOD, DisabledAnnotatedMultiValue.class);
		assertMethodIsDisabled(DISABLED_ANNOTATED_METHOD, DisabledAnnotatedMultiValue.class);
	}
