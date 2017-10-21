		public UOWActionAdapter(TransactionDefinition definition, TransactionCallback<T> callback,
				boolean actualTransaction, boolean newTransaction, boolean newSynchronization, boolean debug) {

			this.definition = definition;
			this.callback = callback;
			this.actualTransaction = actualTransaction;
			this.newTransaction = newTransaction;
			this.newSynchronization = newSynchronization;
			this.debug = debug;
		}
