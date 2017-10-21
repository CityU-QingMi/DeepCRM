	@SuppressWarnings({ "" })
	public void render(StringBuilder jpaqlQuery, RenderingContext renderingContext) {
		jpaqlQuery.append( "select " );
		if ( isDistinct() ) {
			jpaqlQuery.append( "distinct " );
		}
		if ( getSelection() == null ) {
			jpaqlQuery.append( locateImplicitSelection().renderProjection( renderingContext ) );
		}
		else {
			jpaqlQuery.append( ( (Renderable) getSelection() ).renderProjection( renderingContext ) );
		}

		renderFromClause( jpaqlQuery, renderingContext );

		if ( getRestriction() != null) {
			jpaqlQuery.append( " where " )
					.append( ( (Renderable) getRestriction() ).render( renderingContext ) );
		}

		if ( ! getGroupings().isEmpty() ) {
			jpaqlQuery.append( " group by " );
			String sep = "";
			for ( Expression grouping : getGroupings() ) {
				jpaqlQuery.append( sep )
						.append( ( (Renderable) grouping ).render( renderingContext ) );
				sep = ", ";
			}

			if ( getHaving() != null ) {
				jpaqlQuery.append( " having " )
						.append( ( (Renderable) getHaving() ).render( renderingContext ) );
			}
		}
	}
