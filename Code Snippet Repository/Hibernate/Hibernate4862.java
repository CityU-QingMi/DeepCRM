		@Override
		public void perform(ServiceRegistry serviceRegistry) {
			log.startingDelayedSchemaDrop();

			final JdbcContext jdbcContext = new JdbcContextDelayedDropImpl( serviceRegistry );
			final GenerationTargetToDatabase target = new GenerationTargetToDatabase(
					serviceRegistry.getService( TransactionCoordinatorBuilder.class ).buildDdlTransactionIsolator( jdbcContext ),
					true
			);

			target.prepare();
			try {
				for ( String command : commands ) {
					try {
						target.accept( command );
					}
					catch (CommandAcceptanceException e) {
						// implicitly we do not "halt on error", but we do want to
						// report the problem
						log.unsuccessfulSchemaManagementCommand( command );
						log.debugf( e, "Error performing delayed DROP command [%s]", command );
					}
				}
			}
			finally {
				target.release();
			}
		}
