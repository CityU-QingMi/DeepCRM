	public String execute() throws Exception {
		if (catId < 1) {
			// force the root
			catId = 1;
		}

		category = Category.getById(catId);

		return SUCCESS;
	}
