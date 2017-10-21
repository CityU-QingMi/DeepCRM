	@Test
	public void testChainedComparatorsReversed() {
		Comparator<Dog> c = (new PropertyComparator<Dog>("lastName", false, true)).
				thenComparing(new PropertyComparator<>("firstName", false, true));

		Dog dog1 = new Dog();
		dog1.setFirstName("macy");
		dog1.setLastName("grayspots");

		Dog dog2 = new Dog();
		dog2.setFirstName("biscuit");
		dog2.setLastName("grayspots");

		assertTrue(c.compare(dog1, dog2) > 0);
		c = c.reversed();
		assertTrue(c.compare(dog1, dog2) < 0);
	}
