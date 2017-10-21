	public ToolingHintContext(ToolingHintContext baseline) {
		if ( baseline == null ) {
			return;
		}

		for ( ToolingHint toolingHint : baseline.toolingHintMap.values() ) {
			if ( toolingHint.isInheritable() ) {
				toolingHintMap.put( toolingHint.getName(), toolingHint );
			}
		}
	}
