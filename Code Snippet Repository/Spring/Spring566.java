	public static String[] beanNamesForAnnotationIncludingAncestors(
			ListableBeanFactory lbf, Class<? extends Annotation> annotationType) {
		Assert.notNull(lbf, "ListableBeanFactory must not be null");
		String[] result = lbf.getBeanNamesForAnnotation(annotationType);
		if (lbf instanceof HierarchicalBeanFactory) {
			HierarchicalBeanFactory hbf = (HierarchicalBeanFactory) lbf;
			if (hbf.getParentBeanFactory() instanceof ListableBeanFactory) {
				String[] parentResult = beanNamesForAnnotationIncludingAncestors(
						(ListableBeanFactory) hbf.getParentBeanFactory(), annotationType);
				List<String> resultList = new ArrayList<>();
				resultList.addAll(Arrays.asList(result));
				for (String beanName : parentResult) {
					if (!resultList.contains(beanName) && !hbf.containsLocalBean(beanName)) {
						resultList.add(beanName);
					}
				}
				result = StringUtils.toStringArray(resultList);
			}
		}
		return result;
	}
