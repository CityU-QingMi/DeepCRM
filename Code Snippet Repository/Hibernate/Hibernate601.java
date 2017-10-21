	public StandardScanOptions(String explicitDetectionSetting, boolean persistenceUnitExcludeUnlistedClassesValue) {
		if ( explicitDetectionSetting == null ) {
			detectHibernateMappingFiles = true;
			detectClassesInRoot = ! persistenceUnitExcludeUnlistedClassesValue;
			detectClassesInNonRoot = true;
		}
		else {
			detectHibernateMappingFiles = explicitDetectionSetting.contains( "hbm" );
			detectClassesInRoot = explicitDetectionSetting.contains( "class" );
			detectClassesInNonRoot = detectClassesInRoot;
		}
	}
