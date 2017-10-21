	@Test
	public void testProfilePermutations() {
		assertThat(beanFactoryFor(PROD_ELIGIBLE_XML, NONE_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(PROD_ELIGIBLE_XML, DEV_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(PROD_ELIGIBLE_XML, PROD_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(PROD_ELIGIBLE_XML, MULTI_ACTIVE), containsTargetBean());

		assertThat(beanFactoryFor(DEV_ELIGIBLE_XML, NONE_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(DEV_ELIGIBLE_XML, DEV_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(DEV_ELIGIBLE_XML, PROD_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(DEV_ELIGIBLE_XML, MULTI_ACTIVE), containsTargetBean());

		assertThat(beanFactoryFor(NOT_DEV_ELIGIBLE_XML, NONE_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(NOT_DEV_ELIGIBLE_XML, DEV_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(NOT_DEV_ELIGIBLE_XML, PROD_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(NOT_DEV_ELIGIBLE_XML, MULTI_ACTIVE), not(containsTargetBean()));

		assertThat(beanFactoryFor(ALL_ELIGIBLE_XML, NONE_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(ALL_ELIGIBLE_XML, DEV_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(ALL_ELIGIBLE_XML, PROD_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(ALL_ELIGIBLE_XML, MULTI_ACTIVE), containsTargetBean());

		assertThat(beanFactoryFor(MULTI_ELIGIBLE_XML, NONE_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_XML, UNKNOWN_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_XML, DEV_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_XML, PROD_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_XML, MULTI_ACTIVE), containsTargetBean());

		assertThat(beanFactoryFor(MULTI_NOT_DEV_ELIGIBLE_XML, NONE_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(MULTI_NOT_DEV_ELIGIBLE_XML, UNKNOWN_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(MULTI_NOT_DEV_ELIGIBLE_XML, DEV_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(MULTI_NOT_DEV_ELIGIBLE_XML, PROD_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(MULTI_NOT_DEV_ELIGIBLE_XML, MULTI_ACTIVE), containsTargetBean());

		assertThat(beanFactoryFor(MULTI_ELIGIBLE_SPACE_DELIMITED_XML, NONE_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_SPACE_DELIMITED_XML, UNKNOWN_ACTIVE), not(containsTargetBean()));
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_SPACE_DELIMITED_XML, DEV_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_SPACE_DELIMITED_XML, PROD_ACTIVE), containsTargetBean());
		assertThat(beanFactoryFor(MULTI_ELIGIBLE_SPACE_DELIMITED_XML, MULTI_ACTIVE), containsTargetBean());

		assertThat(beanFactoryFor(UNKNOWN_ELIGIBLE_XML, MULTI_ACTIVE), not(containsTargetBean()));
	}
