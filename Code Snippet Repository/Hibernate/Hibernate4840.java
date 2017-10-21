	public static ScriptSourceInput interpretScriptSourceSetting(
			Object scriptSourceSetting,
			ClassLoaderService classLoaderService,
			String charsetName ) {
		if ( Reader.class.isInstance( scriptSourceSetting ) ) {
			return new ScriptSourceInputFromReader( (Reader) scriptSourceSetting );
		}
		else {
			final String scriptSourceSettingString = scriptSourceSetting.toString();
			log.debugf( "Attempting to resolve script source setting : %s", scriptSourceSettingString );

			// setting could be either:
			//		1) string URL representation (i.e., "file://...")
			//		2) relative file path (resource lookup)
			//		3) absolute file path

			log.trace( "Trying as URL..." );
			// ClassLoaderService.locateResource() first tries the given resource name as url form...
			final URL url = classLoaderService.locateResource( scriptSourceSettingString );
			if ( url != null ) {
				return new ScriptSourceInputFromUrl( url, charsetName );
			}

			// assume it is a File path
			final File file = new File( scriptSourceSettingString );
			return new ScriptSourceInputFromFile( file, charsetName );
		}
	}
