		@Override
		public void onPostInsert(PostInsertEvent event) {
			event.getSession().getActionQueue().registerProcess(
					new BeforeTransactionCompletionProcess() {
						@Override
						public void doBeforeTransactionCompletion(SessionImplementor session) {
							beforeCounter.increase();
						}
					}
			);
			event.getSession().getActionQueue().registerProcess(
					new AfterTransactionCompletionProcess() {
						@Override
						public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor session) {
							afterCounter.increase();
						}
					}
			);
		}
