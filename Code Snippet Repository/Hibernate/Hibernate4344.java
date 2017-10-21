	@Override
	public Expression<T> getSelection() {
		if ( wrappedSelection == null ) {
			if ( queryStructure.getSelection() == null ) {
				return null;
			}
			wrappedSelection = new SubquerySelection<T>( (ExpressionImpl<T>) queryStructure.getSelection(), this );
		}
		return wrappedSelection;
	}
