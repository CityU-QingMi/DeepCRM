	private void assertTxProxying(AnnotationConfigApplicationContext ctx) {
		FooRepository repo = ctx.getBean(FooRepository.class);

		boolean isTxProxy = false;
		if (AopUtils.isAopProxy(repo)) {
			for (Advisor advisor : ((Advised)repo).getAdvisors()) {
				if (advisor instanceof BeanFactoryTransactionAttributeSourceAdvisor) {
					isTxProxy = true;
					break;
				}
			}
		}
		assertTrue("FooRepository is not a TX proxy", isTxProxy);

		// trigger a transaction
		repo.findAll();
	}
