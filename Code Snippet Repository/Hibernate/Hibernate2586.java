	@Override
	public void traceOut(String ruleName, AST tree) {
		if ( !LOG.isTraceEnabled() ) {
			return;
		}
		if ( inputState.guessing > 0 ) {
			return;
		}
		String prefix = "<-" + StringHelper.repeat( '-', ( --traceDepth * 2 ) ) + " ";
		LOG.trace( prefix + ruleName );
	}
