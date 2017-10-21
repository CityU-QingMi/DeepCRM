	@Test
	public void contextNesting() {
		TestBean father = (TestBean) this.applicationContext.getBean("father");
		assertTrue("Bean from root context", father != null);
		assertTrue("Custom BeanPostProcessor applied", father.getFriends().contains("myFriend"));

		TestBean rod = (TestBean) this.applicationContext.getBean("rod");
		assertTrue("Bean from child context", "Rod".equals(rod.getName()));
		assertTrue("Bean has external reference", rod.getSpouse() == father);
		assertTrue("Custom BeanPostProcessor not applied", !rod.getFriends().contains("myFriend"));

		rod = (TestBean) this.root.getBean("rod");
		assertTrue("Bean from root context", "Roderick".equals(rod.getName()));
		assertTrue("Custom BeanPostProcessor applied", rod.getFriends().contains("myFriend"));
	}
