	private ScriptSourceInput interpretLegacyImportScriptSetting(
			String resourceName,
			ClassLoaderService classLoaderService,
			String charsetName) {
		try {
			final URL resourceUrl = classLoaderService.locateResource( resourceName );
			if ( resourceUrl == null ) {
				return ScriptSourceInputNonExistentImpl.INSTANCE;
			}
			else {
				return new ScriptSourceInputFromUrl( resourceUrl, charsetName );
			}
		}
		catch (Exception e) {
			throw new SchemaManagementException( "Error resolving legacy import resource : " + resourceName, e );
		}
	}
