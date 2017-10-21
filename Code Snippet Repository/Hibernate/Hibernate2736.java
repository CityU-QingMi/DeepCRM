	@SuppressWarnings("")
	private boolean isReturnableEntity(SelectExpression selectExpression) throws SemanticException {
		FromElement fromElement = selectExpression.getFromElement();
		boolean isFetchOrValueCollection = fromElement != null &&
				( fromElement.isFetch() || fromElement.isCollectionOfValuesOrComponents() );
		if ( isFetchOrValueCollection ) {
			return false;
		}
		else {
			return selectExpression.isReturnableEntity();
		}
	}
