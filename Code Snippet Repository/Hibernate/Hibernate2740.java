	private void renderNonScalarProperties(
			ASTAppender appender,
			SelectExpression selectExpression,
			FromElement fromElement,
			int nonscalarSize,
			int k) {
		final String text;
		if ( selectExpression instanceof MapKeyNode ) {
			final MapKeyNode mapKeyNode = (MapKeyNode) selectExpression;
			if ( mapKeyNode.getMapKeyEntityFromElement() != null ) {
				text = mapKeyNode.getMapKeyEntityFromElement().renderMapKeyPropertySelectFragment( nonscalarSize, k );
			}
			else {
				text = fromElement.renderPropertySelect( nonscalarSize, k );
			}
		}
		else if ( selectExpression instanceof MapEntryNode ) {
			text = fromElement.renderMapEntryPropertySelectFragment( nonscalarSize, k );
		}
		else {
			text = fromElement.renderPropertySelect( nonscalarSize, k );
		}
		appender.append( SqlTokenTypes.SQL_TOKEN, text, false );

		if ( fromElement.getQueryableCollection() != null && fromElement.isFetch() ) {
			String subText1 = fromElement.renderCollectionSelectFragment( nonscalarSize, k );
			appender.append( SqlTokenTypes.SQL_TOKEN, subText1, false );
		}

		// Look through the FromElement's children to find any collections of values that should be fetched...
		ASTIterator itr = new ASTIterator( fromElement );
		while ( itr.hasNext() ) {
			FromElement child = (FromElement) itr.next();
			if ( child.isCollectionOfValuesOrComponents() && child.isFetch() ) {
				// Need a better way to define the suffixes here...
				final String subText2 = child.renderValueCollectionSelectFragment( nonscalarSize, nonscalarSize + k );
				appender.append( SqlTokenTypes.SQL_TOKEN, subText2, false );
			}
		}
	}
