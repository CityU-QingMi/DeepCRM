	@Override
	public List<ValueHandlerFactory.ValueHandler> getValueHandlers() {
		if ( isConstructor ) {
			return null;
		}
		boolean foundHandlers = false;
		ArrayList<ValueHandlerFactory.ValueHandler> valueHandlers = new ArrayList<ValueHandlerFactory.ValueHandler>();
		for ( Selection selection : getCompoundSelectionItems() ) {
			ValueHandlerFactory.ValueHandler valueHandler = ( (TupleElementImplementor) selection ).getValueHandler();
			valueHandlers.add( valueHandler );
			foundHandlers = foundHandlers || valueHandler != null;
		}
		return foundHandlers ? null : valueHandlers;
	}
