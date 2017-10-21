	@Override
	public int bind(
			PreparedStatement statement,
			QueryParameters qp,
			SharedSessionContractImplementor session,
			int start) throws SQLException {
		final int columnSpan = definedParameterType.getColumnSpan( session.getFactory() );
		final Object value = session.getLoadQueryInfluencers().getFilterParameterValue( filterName + '.' + parameterName );
		if ( Collection.class.isInstance( value ) ) {
			int positions = 0;
			Iterator itr = ( ( Collection ) value ).iterator();
			while ( itr.hasNext() ) {
				definedParameterType.nullSafeSet( statement, itr.next(), start + positions, session );
				positions += columnSpan;
			}
			return positions;
		}
		else {
			definedParameterType.nullSafeSet( statement, value, start, session );
			return columnSpan;
		}
	}
