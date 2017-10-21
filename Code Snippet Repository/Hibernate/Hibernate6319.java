	@Override
	protected void configure(Configuration cfg) {
		super.configure( cfg );
		final MessageInterpolator messageInterpolator = new MessageInterpolator() {

			public String interpolate(String s, Context context) {
				return "Oops";
			}

			public String interpolate(String s, Context context, Locale locale) {
				return interpolate( s, context );
			}
		};
		final javax.validation.Configuration<?> configuration = Validation.byDefaultProvider().configure();
		configuration.messageInterpolator( messageInterpolator );
		ValidatorFactory vf = configuration.buildValidatorFactory();
		cfg.getProperties().put( "javax.persistence.validation.factory", vf );
		cfg.setProperty( "javax.persistence.validation.mode", "AUTO" );
	}
