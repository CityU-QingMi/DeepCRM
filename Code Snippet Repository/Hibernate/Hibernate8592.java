	@Override
	public void configure(Configuration cfg) {
		super.configure( cfg );
		if ( !useAntlrParser ) {
			cfg.setProperty( Environment.QUERY_TRANSLATOR, ClassicQueryTranslatorFactory.class.getName() );
			try {
				String dialectTrueRepresentation = Dialect.getDialect().toBooleanValueString( true );
				// if this call succeeds, then the dialect is saying to represent true/false as int values...
				Integer.parseInt( dialectTrueRepresentation );
				String subs = cfg.getProperties().getProperty( Environment.QUERY_SUBSTITUTIONS );
				if ( subs == null ) {
					subs = "";
				}
				if ( StringHelper.isEmpty( subs ) ) {
					subs = "true=1, false=0";
				}
				else {
					subs += ", true=1, false=0";
				}
				cfg.getProperties().setProperty( Environment.QUERY_SUBSTITUTIONS, subs );
//				cfg.setNamingStrategy( DefaultNamingStrategy.INSTANCE );
			}
			catch( NumberFormatException nfe ) {
				// the Integer#parseInt call failed...
			}
		}
	}
