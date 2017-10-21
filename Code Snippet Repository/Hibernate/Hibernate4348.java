	private void renderAssignments(StringBuilder jpaql, RenderingContext renderingContext) {
		jpaql.append( " set " );
		boolean first = true;
		for ( Assignment assignment : assignments ) {
			if ( ! first ) {
				jpaql.append( ", " );
			}
			jpaql.append( assignment.attributePath.render( renderingContext ) )
					.append( " = " )
					.append( assignment.value.render( renderingContext ) );
			first = false;
		}
	}
