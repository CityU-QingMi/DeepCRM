	private static ToolingHintContext collectToolingHints(
			ToolingHintContext baseline,
			ToolingHintContainer toolingHintContainer,
			boolean onlyInheritable) {
		final ToolingHintContext localToolingHints = new ToolingHintContext( baseline );

		if ( toolingHintContainer != null && toolingHintContainer.getToolingHints() != null ) {
			for ( JaxbHbmToolingHintType toolingHintJaxbBinding : toolingHintContainer.getToolingHints() ) {
				if ( onlyInheritable && !toolingHintJaxbBinding.isInheritable() ) {
					continue;
				}

				final String hintName = toolingHintJaxbBinding.getName();
				ToolingHint toolingHint = localToolingHints.getToolingHint( hintName );

				if ( toolingHint == null ) {
					toolingHint = new ToolingHint( hintName, toolingHintJaxbBinding.isInheritable() );
					localToolingHints.add( toolingHint );
				}
				else {
					if ( baseline != null ) {
						final ToolingHint inherited = baseline.getToolingHint( hintName );
						if ( toolingHint == inherited ) {
							// overriding inherited meta attribute. HBX-621 & HBX-793
							toolingHint = new ToolingHint( hintName, toolingHintJaxbBinding.isInheritable() );
							localToolingHints.add( toolingHint );
						}
					}
				}

				toolingHint.addValue( toolingHintJaxbBinding.getValue() );
			}
		}

		return localToolingHints;
	}
