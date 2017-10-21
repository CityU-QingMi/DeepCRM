	private static JpaTargetAndSourceDescriptor buildDatabaseTargetDescriptor(
			Map configurationValues,
			SettingSelector settingSelector,
			ServiceRegistry serviceRegistry) {
		final Object scriptSourceSetting = settingSelector.getScriptSourceSetting( configurationValues );
		final SourceType sourceType = SourceType.interpret(
				settingSelector.getSourceTypeSetting( configurationValues ),
				scriptSourceSetting != null ? SourceType.SCRIPT : SourceType.METADATA
		);

		final boolean includesScripts = sourceType != SourceType.METADATA;
		if ( includesScripts && scriptSourceSetting == null ) {
			throw new SchemaManagementException(
					"Schema generation configuration indicated to include CREATE scripts, but no script was specified"
			);
		}

		final ScriptSourceInput scriptSourceInput = includesScripts ?
				Helper.interpretScriptSourceSetting(
						scriptSourceSetting,
						serviceRegistry.getService( ClassLoaderService.class ),
						(String) configurationValues.get( AvailableSettings.HBM2DDL_CHARSET_NAME )
				)
				: null;

		return new JpaTargetAndSourceDescriptor() {
			@Override
			public EnumSet<TargetType> getTargetTypes() {
				return EnumSet.of( TargetType.DATABASE );
			}

			@Override
			public ScriptTargetOutput getScriptTargetOutput() {
				return null;
			}

			@Override
			public SourceType getSourceType() {
				return sourceType;
			}

			@Override
			public ScriptSourceInput getScriptSourceInput() {
				return scriptSourceInput;
			}
		};
	}
