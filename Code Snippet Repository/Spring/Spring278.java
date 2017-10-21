	@Test
	public void testMatchVarargs() throws SecurityException, NoSuchMethodException {

		@SuppressWarnings("unused")
		class MyTemplate {
			public int queryForInt(String sql, Object... params) {
				return 0;
			}
		}

		String expression = "execution(int *.*(String, Object...))";
		AspectJExpressionPointcut jdbcVarArgs = new AspectJExpressionPointcut();
		jdbcVarArgs.setExpression(expression);

		// TODO: the expression above no longer matches Object[]
		// assertFalse(jdbcVarArgs.matches(
		//	JdbcTemplate.class.getMethod("queryForInt", String.class, Object[].class),
		//	JdbcTemplate.class));

		assertTrue(jdbcVarArgs.matches(
				MyTemplate.class.getMethod("queryForInt", String.class, Object[].class),
				MyTemplate.class));

		Method takesGenericList = methodsOnHasGeneric.get("setFriends");
		assertFalse(jdbcVarArgs.matches(takesGenericList, HasGeneric.class));
		assertFalse(jdbcVarArgs.matches(methodsOnHasGeneric.get("setEnemies"), HasGeneric.class));
		assertFalse(jdbcVarArgs.matches(methodsOnHasGeneric.get("setPartners"), HasGeneric.class));
		assertFalse(jdbcVarArgs.matches(methodsOnHasGeneric.get("setPhoneNumbers"), HasGeneric.class));
		assertFalse(jdbcVarArgs.matches(getAge, TestBean.class));
	}
