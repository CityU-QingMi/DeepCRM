	@Override
	public Map<String, Object> getHints() {
		// Technically this should rollback, but that's insane :)
		// If the TCK ever adds a check for this, we may need to change this behavior
		getProducer().checkOpen( false );

		final Map<String,Object> hints = new HashMap<>();
		collectBaselineHints( hints );
		collectHints( hints );
		return hints;
	}
