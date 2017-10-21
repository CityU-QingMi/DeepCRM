	@Test
	public void testNative() {
		test(new Binder() {

			public void bind(Query q) {
				org.hibernate.Query hibernateQuery = q.unwrap(org.hibernate.Query.class);
				hibernateQuery.setParameter("tags", Arrays.asList("important","business"), new CustomType(TagUserType.INSTANCE));
			}
		});
	}
