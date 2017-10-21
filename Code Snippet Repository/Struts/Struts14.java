	public String execute() throws Exception {
		if ("fruits".equals(select)) {
			options.add("apple");
			options.add("banana");
			options.add("grape");
			options.add("pear");
		} else if ("colors".equals(select)) {
			options.add("red");
			options.add("green");
			options.add("blue");
		}
		return SUCCESS;
	}
