	@Override
	public ResultTransformer getResultTransformer() {
		if ( constructor != null ) {
			return new AliasToBeanConstructorResultTransformer( constructor );
		}
		else if ( isMap ) {
			return Transformers.ALIAS_TO_ENTITY_MAP;
		}
		else if ( isList ) {
			return Transformers.TO_LIST;
		}
		throw new QueryException( "Unable to determine proper dynamic-instantiation tranformer to use." );
	}
