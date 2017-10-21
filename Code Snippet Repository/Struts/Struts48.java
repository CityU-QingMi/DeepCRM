	public String input() throws Exception {
		// prepare input form
		Integer balSource = (Integer) ActionContext.getContext().getSession().get("balanceSource");
		Integer balDest = (Integer) ActionContext.getContext().getSession().get("balanceDestination");

		if (balSource == null) {
			// first time set up an initial account balance
			balSource = new Integer(1200);
			ActionContext.getContext().getSession().put("balanceSource", balSource);
		}

		if (balDest == null) {
			// first time set up an initial account balance
			balDest = new Integer(2500);
			ActionContext.getContext().getSession().put("balanceDestination", balDest);
		}

		return INPUT;
	}
