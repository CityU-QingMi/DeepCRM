		private ExtendedEntityManagerInvocationHandler(EntityManager target,
				@Nullable PersistenceExceptionTranslator exceptionTranslator, @Nullable Boolean jta,
				boolean containerManaged, boolean synchronizedWithTransaction) {

			this.target = target;
			this.exceptionTranslator = exceptionTranslator;
			this.jta = (jta != null ? jta : isJtaEntityManager());
			this.containerManaged = containerManaged;
			this.synchronizedWithTransaction = synchronizedWithTransaction;
		}
