	@Test
	public void appendList() {
		List<SomeObject> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		String str = new ToStringCreator(this).append("myLetters", list).toString();
		assertEquals("[ToStringCreatorTests@" + ObjectUtils.getIdentityHexString(this) + " myLetters = list[A, B, C]]",
				str);
	}
