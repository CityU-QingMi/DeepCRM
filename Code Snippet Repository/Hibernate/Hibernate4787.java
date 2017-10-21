	public static EnumSet<TargetType> parseLegacyCommandLineOptions(boolean script, boolean export, String outputFile) {
		final EnumSet<TargetType> options = EnumSet.noneOf( TargetType.class );

		final Target target = Target.interpret( script, export );
		if ( outputFile != null ) {
			options.add( TargetType.SCRIPT );
		}
		if ( target.doScript() ) {
			options.add( TargetType.STDOUT );
		}
		if ( target.doExport() ) {
			options.add( TargetType.DATABASE );
		}
		return options;
	}
