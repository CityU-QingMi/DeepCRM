	public String execute() throws Exception {
		// transfer from source to destination

		Integer balSource = (Integer) ActionContext.getContext().getSession().get("balanceSource");
		Integer balDest = (Integer) ActionContext.getContext().getSession().get("balanceDestination");

		Integer newSource = new Integer(balSource.intValue() - amount);
		Integer newDest = new Integer(balDest.intValue() + amount);

		ActionContext.getContext().getSession().put("balanceSource", newSource);
		ActionContext.getContext().getSession().put("balanceDestination", newDest);
		ActionContext.getContext().getSession().put("time", new Date());

		Thread.sleep(2000); // to simulate processing time

		return SUCCESS;
	}
