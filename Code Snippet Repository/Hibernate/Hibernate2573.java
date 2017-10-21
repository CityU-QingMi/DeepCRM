		public void visit(AST node) {
			// TODO : currently expects that the individual with expressions apply to the same sql table join.
			//      This may not be the case for joined-subclass where the property values
			//      might be coming from different tables in the joined hierarchy.  At some
			//      point we should expand this to support that capability.  However, that has
			//      some difficulties:
			//          1) the biggest is how to handle ORs when the individual comparisons are
			//              linked to different sql joins.
			//          2) here we would need to track each comparison individually, along with
			//              the join alias to which it applies and then pass that information
			//              back to the FromElement so it can pass it along to the JoinSequence
			if ( node instanceof DotNode ) {
				DotNode dotNode = (DotNode) node;
				FromElement fromElement = dotNode.getFromElement();
				if ( referencedFromElement != null ) {
//					if ( fromElement != referencedFromElement ) {
//						throw new HibernateException( "with-clause referenced two different from-clause elements" );
//					}
				}
				else {
					referencedFromElement = fromElement;
					joinAlias = extractAppliedAlias( dotNode );
					// TODO : temporary
					//      needed because currently persister is the one that
					// creates and renders the join fragments for inheritance
					//      hierarchies...
//					if ( !joinAlias.equals( referencedFromElement.getTableAlias() ) ) {
//						throw new InvalidWithClauseException(
//								"with clause can only reference columns in the driving table",
//								queryTranslatorImpl.getQueryString()
//						);
//					}
				}
			}
			else if ( node instanceof ParameterNode ) {
				applyParameterSpecification( ( (ParameterNode) node ).getHqlParameterSpecification() );
			}
			else if ( node instanceof ParameterContainer ) {
				applyParameterSpecifications( (ParameterContainer) node );
			}
		}
