		@Override
		protected void renderArguments(StringBuilder buffer, RenderingContext renderingContext) {
			if ( isDistinct() ) {
				buffer.append("distinct ");
			}
			else {
	            // If function specifies a single non-distinct entity with ID, its alias would normally be rendered, which ends up
	            // converting to the column(s) associated with the entity's ID in the rendered SQL.  However, some DBs don't support
	            // the multiple columns that would end up here for entities with composite IDs.  So, since we modify the query to
	            // instead specify star since that's functionally equivalent and supported by all DBs.
				List<Expression<?>> argExprs = getArgumentExpressions();
				if (argExprs.size() == 1) {
					Expression argExpr = argExprs.get(0);
					if (argExpr instanceof Root<?>) {
						Root<?> root = (Root<?>)argExpr;
						if (!root.getModel().hasSingleIdAttribute()) {
							buffer.append('*');
							return;
						}
					}
				}
			}
			super.renderArguments(buffer, renderingContext);
		}
