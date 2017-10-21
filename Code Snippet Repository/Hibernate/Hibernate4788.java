	public static EnumSet<TargetType> parseCommandLineOptions(String targetTypeText) {
		final EnumSet<TargetType> options = EnumSet.noneOf( TargetType.class );

		if ( !targetTypeText.equalsIgnoreCase( "none" ) ) {
			for ( String option : targetTypeText.split( "," ) ) {
				if ( option.equalsIgnoreCase( "database" ) ) {
					options.add( TargetType.DATABASE );
				}
				else if ( option.equalsIgnoreCase( "stdout" ) ) {
					options.add( TargetType.STDOUT );
				}
				else if ( option.equalsIgnoreCase( "script" ) ) {
					options.add( TargetType.SCRIPT );
				}
				else {
					throw new IllegalArgumentException( "Unrecognized --target option : " + option );
				}
			}
		}

		return options;
	}
