	private void assertCacheProxying(AnnotationConfigApplicationContext ctx) {
		FooRepository repo = ctx.getBean(FooRepository.class);

		boolean isCacheProxy = false;
		if (AopUtils.isAopProxy(repo)) {
			for (Advisor advisor : ((Advised)repo).getAdvisors()) {
				if (advisor instanceof BeanFactoryCacheOperationSourceAdvisor) {
					isCacheProxy = true;
					break;
				}
			}
		}
		assertTrue("FooRepository is not a cache proxy", isCacheProxy);
	}
