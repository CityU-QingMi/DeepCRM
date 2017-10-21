	@Test
	@SuppressWarnings("")
	public void expressionIsCacheBasedOnActualMethod() {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(SharedConfig.class, Spr11692Config.class);

		BaseDao<User> userDao = (BaseDao<User>) context.getBean("userDao");
		BaseDao<Order> orderDao = (BaseDao<Order>) context.getBean("orderDao");

		userDao.persist(new User("1"));
		orderDao.persist(new Order("2"));

		context.close();
	}
