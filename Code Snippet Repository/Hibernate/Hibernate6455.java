	public CustomerTwo(String first, String last,
					String contact, String credit, BigDecimal creditLimit,
					BigDecimal balance, BigDecimal YtdPayment) {

		this.firstName = first;
		this.lastName = last;
		this.contact = contact;
		this.since = Calendar.getInstance();
		this.credit = credit;
		this.creditLimit = creditLimit;
		this.balance = balance;
		this.ytdPayment = YtdPayment;
	}
