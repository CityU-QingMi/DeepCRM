	public Contract(Plan plan, String customerName, String type) {
		plans = new HashSet();
		if ( plan != null ) {
			plans.add( plan );
			plan.getContracts().add( this );
		}
		this.customerName = customerName;
		this.type = type;
		variations = new ArrayList();
		subcontracts = new HashSet();
		parties = new HashSet();
		infos = new HashSet();
	}
