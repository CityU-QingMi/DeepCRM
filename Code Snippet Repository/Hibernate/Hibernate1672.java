	public Map<String, Object> getNaturalIdValues() {
		final Map<String, Object> naturalIdValueMap = new ConcurrentHashMap<String, Object>();
		for ( Criterion condition : conjunction.conditions() ) {
			if ( !SimpleExpression.class.isInstance( condition ) ) {
				continue;
			}
			final SimpleExpression equalsCondition = SimpleExpression.class.cast( condition );
			if ( !"=".equals( equalsCondition.getOp() ) ) {
				continue;
			}

			naturalIdValueMap.put( equalsCondition.getPropertyName(), equalsCondition.getValue() );
		}
		return naturalIdValueMap;
	}
