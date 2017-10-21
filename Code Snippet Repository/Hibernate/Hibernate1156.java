	private void initTransients() {
		this.toString = new ValueHolder<>(
				new ValueHolder.DeferredInitializer<String>() {
					@Override
					public String initialize() {
						//Complex toString is needed as naturalIds for entities are not simply based on a single value like primary keys
						//the only same way to differentiate the keys is to included the disassembled values in the string.
						final StringBuilder toStringBuilder = new StringBuilder().append( entityName ).append(
								"##NaturalId[" );
						for ( int i = 0; i < naturalIdValues.length; i++ ) {
							toStringBuilder.append( naturalIdValues[i] );
							if ( i + 1 < naturalIdValues.length ) {
								toStringBuilder.append( ", " );
							}
						}
						toStringBuilder.append( "]" );

						return toStringBuilder.toString();
					}
				}
		);
	}
