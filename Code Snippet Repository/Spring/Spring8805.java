	protected PersistenceExceptionTranslator detectPersistenceExceptionTranslators(ListableBeanFactory beanFactory) {
		// Find all translators, being careful not to activate FactoryBeans.
		Map<String, PersistenceExceptionTranslator> pets = BeanFactoryUtils.beansOfTypeIncludingAncestors(
				beanFactory, PersistenceExceptionTranslator.class, false, false);
		ChainedPersistenceExceptionTranslator cpet = new ChainedPersistenceExceptionTranslator();
		for (PersistenceExceptionTranslator pet : pets.values()) {
			cpet.addDelegate(pet);
		}
		return cpet;
	}
