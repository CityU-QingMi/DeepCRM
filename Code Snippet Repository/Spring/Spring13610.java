	@Override
	protected TestBean createTestBean() {
		this.bean = new TestBeanWithRealCountry();
		this.bean.setName("Rob");
		this.bean.setCountry("UK");
		this.bean.setSex("M");
		this.bean.setMyFloat(new Float("12.34"));
		this.bean.setSomeIntegerArray(new Integer[]{new Integer(12), new Integer(34)});
		return this.bean;
	}
