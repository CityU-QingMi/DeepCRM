	@Test
	public void testJpa() {
		test(new Binder() {

			public void bind(Query q) {
				q.setParameter("tags", new TypedParameterValue( new CustomType( TagUserType.INSTANCE), Arrays.asList("important","business")));
			}
		});

	}
