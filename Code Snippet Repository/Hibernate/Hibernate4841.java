	public static ScriptTargetOutput interpretScriptTargetSetting(
			Object scriptTargetSetting,
			ClassLoaderService classLoaderService,
			String charsetName ) {
		if ( scriptTargetSetting == null ) {
			return null;
		}
		else if ( Writer.class.isInstance( scriptTargetSetting ) ) {
			return new ScriptTargetOutputToWriter( (Writer) scriptTargetSetting );
		}
		else {
			final String scriptTargetSettingString = scriptTargetSetting.toString();
			log.debugf( "Attempting to resolve script source setting : %s", scriptTargetSettingString );

			// setting could be either:
			//		1) string URL representation (i.e., "file://...")
			//		2) relative file path (resource lookup)
			//		3) absolute file path

			log.trace( "Trying as URL..." );
			// ClassLoaderService.locateResource() first tries the given resource name as url form...
			final URL url = classLoaderService.locateResource( scriptTargetSettingString );
			if ( url != null ) {
				return new ScriptTargetOutputToUrl( url, charsetName );
			}

			// assume it is a File path
			final File file = new File( scriptTargetSettingString );
			return new ScriptTargetOutputToFile( file, charsetName );
		}
	}
