	public void handlePropertyBeingDereferenced(Type propertySource, String propertyName) {
		if ( getQueryableCollection() != null && CollectionProperties.isCollectionProperty( propertyName ) ) {
			// propertyName refers to something like collection.size...
			return;
		}
		if ( propertySource.isComponentType() ) {
			// property name is a sub-path of a component...
			return;
		}

		Queryable persister = getQueryable();
		if ( persister != null ) {
			try {
				Queryable.Declarer propertyDeclarer = persister.getSubclassPropertyDeclarer( propertyName );
				if ( LOG.isTraceEnabled() ) {
					LOG.tracev( "Handling property dereference [{0} ({1}) -> {2} ({3})]",
							persister.getEntityName(), getClassAlias(), propertyName, propertyDeclarer );
				}
				if ( propertyDeclarer == Queryable.Declarer.SUBCLASS ) {
					dereferencedBySubclassProperty = true;
					includeSubclasses = true;
				}
				else if ( propertyDeclarer == Queryable.Declarer.SUPERCLASS ) {
					dereferencedBySuperclassProperty = true;
				}
			}
			catch( QueryException ignore ) {
				// ignore it; the incoming property could not be found so we
				// cannot be sure what to do here.  At the very least, the
				// safest is to simply not apply any dereference toggling...

			}
		}
	}
