	private int calculatePositionalValueSpan(boolean reserveFirstParameter) {
		int positionalValueSpan = 0;
		for ( QueryParameterBinding binding : positionalParameterBindings.values() ) {
			if ( binding.isBound() ) {
				Type bindType = binding.getBindType();
				if ( bindType == null ) {
					bindType = SerializableType.INSTANCE;
				}
				positionalValueSpan += bindType.getColumnSpan( sessionFactory );
			}
		}
		return positionalValueSpan;
	}
