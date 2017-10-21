	public static Set<ValidationMode> getModes(Object modeProperty) {
		Set<ValidationMode> modes = new HashSet<ValidationMode>(3);
		if (modeProperty == null) {
			modes.add( ValidationMode.AUTO );
		}
		else {
			final String[] modesInString = modeProperty.toString().split( "," );
			for ( String modeInString : modesInString ) {
				modes.add( getMode(modeInString) );
			}
		}
		if ( modes.size() > 1 && ( modes.contains( ValidationMode.AUTO ) || modes.contains( ValidationMode.NONE ) ) ) {
			throw new HibernateException( "Incompatible validation modes mixed: " +  loggable( modes ) );
		}
		return modes;
	}
