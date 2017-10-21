		@Override
		public void objectRenamed(NamingEvent evt) {
			final String oldJndiName = evt.getOldBinding().getName();
			final String newJndiName = evt.getNewBinding().getName();

			LOG.factoryJndiRename( oldJndiName, newJndiName );

			final String uuid = nameUuidXref.remove( oldJndiName );
			nameUuidXref.put( newJndiName, uuid );
		}
