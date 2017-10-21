	public static EntityManagerFactory findEntityManagerFactory(
			ListableBeanFactory beanFactory, @Nullable String unitName) throws NoSuchBeanDefinitionException {

		Assert.notNull(beanFactory, "ListableBeanFactory must not be null");
		if (StringUtils.hasLength(unitName)) {
			// See whether we can find an EntityManagerFactory with matching persistence unit name.
			String[] candidateNames =
					BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, EntityManagerFactory.class);
			for (String candidateName : candidateNames) {
				EntityManagerFactory emf = (EntityManagerFactory) beanFactory.getBean(candidateName);
				if (emf instanceof EntityManagerFactoryInfo) {
					if (unitName.equals(((EntityManagerFactoryInfo) emf).getPersistenceUnitName())) {
						return emf;
					}
				}
			}
			// No matching persistence unit found - simply take the EntityManagerFactory
			// with the persistence unit name as bean name (by convention).
			return beanFactory.getBean(unitName, EntityManagerFactory.class);
		}
		else {
			// Find unique EntityManagerFactory bean in the context, falling back to parent contexts.
			return beanFactory.getBean(EntityManagerFactory.class);
		}
	}
