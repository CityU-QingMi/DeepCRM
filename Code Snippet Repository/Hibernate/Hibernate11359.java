	private void configureTransactionManager(ConfigurationBuilder builder) {
		TransactionConfiguration transaction = builder.transaction().create();
		if (transaction.transactionMode().isTransactional() ) {
			final String ispnTmLookupClassName = transaction.transactionManagerLookup().getClass().getName();
			final String hbTmLookupClassName = org.hibernate.cache.infinispan.tm.HibernateTransactionManagerLookup.class.getName();
			if ( GenericTransactionManagerLookup.class.getName().equals( ispnTmLookupClassName ) ) {
				log.debug(
						"Using default Infinispan transaction manager lookup " +
								"instance (GenericTransactionManagerLookup), overriding it " +
								"with Hibernate transaction manager lookup"
				);
				builder.transaction().transactionManagerLookup( transactionManagerlookup );
			}
			else if ( ispnTmLookupClassName != null && !ispnTmLookupClassName.equals( hbTmLookupClassName ) ) {
				log.debug(
						"Infinispan is configured [" + ispnTmLookupClassName + "] with a different transaction manager lookup " +
								"class than Hibernate [" + hbTmLookupClassName + "]"
				);
			}
			else {
				// Infinispan TM lookup class null, so apply Hibernate one directly
				builder.transaction().transactionManagerLookup( transactionManagerlookup );
			}
			builder.transaction().useSynchronization( DEF_USE_SYNCHRONIZATION );
		}
	}
