		@Override
		public void findDirty(final Object entity, EntityPersister persister, Session session, DirtyCheckContext dirtyCheckContext) {
			findDirtyCount++;
			System.out.println( "findDirty called" );
			dirtyCheckContext.doDirtyChecking(
					new AttributeChecker() {
						@Override
						public boolean isDirty(AttributeInformation attributeInformation) {
							return Thing.class.cast( entity ).changedValues.containsKey( attributeInformation.getName() );
						}
					}
			);
		}
