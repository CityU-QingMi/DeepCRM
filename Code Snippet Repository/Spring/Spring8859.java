	@SuppressWarnings("")
	public JtaTransactionManagerFactoryBean() {
		String className = resolveJtaTransactionManagerClassName();
		try {
			Class<? extends JtaTransactionManager> clazz = (Class<? extends JtaTransactionManager>)
					ClassUtils.forName(className, JtaTransactionManagerFactoryBean.class.getClassLoader());
			this.transactionManager = BeanUtils.instantiateClass(clazz);
		}
		catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Failed to load JtaTransactionManager class: " + className, ex);
		}
	}
