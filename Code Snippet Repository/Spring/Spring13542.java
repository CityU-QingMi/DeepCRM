	@Override
	protected void extendRequest(MockHttpServletRequest request) {
		TestBean bean = new TestBean();
		bean.setName("foo");
		bean.setFavouriteColour(Colour.GREEN);
		bean.setStringArray(ARRAY);
		bean.setSpouse(new TestBean("Sally"));
		bean.setSomeNumber(new Float("12.34"));

		List friends = new ArrayList();
		friends.add(new TestBean("bar"));
		friends.add(new TestBean("penc"));
		bean.setFriends(friends);

		request.setAttribute("testBean", bean);
	}
