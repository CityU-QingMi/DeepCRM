	@Test
	@SkipForDialect ( value = { SybaseASE15Dialect.class }, jiraKey = "" )
	@SkipForDialect ( value = { DB2Dialect.class }, jiraKey = "" )
	public void testCustomColumnReadAndWrite() {
		Session s = openSession();
		org.hibernate.Transaction t = s.beginTransaction();
		final BigDecimal AMOUNT = new BigDecimal(73000000d);
		final BigDecimal AMOUNT_MILLIONS = AMOUNT.divide(new BigDecimal(1000000d));
		MutualFund f = new MutualFund();
		f.setHoldings( new MonetoryAmount( AMOUNT, Currency.getInstance("USD") ) );
		s.persist(f);
		s.flush();

		// Test value conversion during insert
		BigDecimal amountViaSql = (BigDecimal)s.createSQLQuery("select amount_millions from MutualFund").uniqueResult();
		assertEquals(AMOUNT_MILLIONS.doubleValue(), amountViaSql.doubleValue(), 0.01d);
		
		// Test projection
		BigDecimal amountViaHql = (BigDecimal)s.createQuery("select f.holdings.amount from MutualFund f").uniqueResult();
		assertEquals(AMOUNT.doubleValue(), amountViaHql.doubleValue(), 0.01d);
		
		// Test restriction and entity load via criteria
		BigDecimal one = new BigDecimal(1);
		f = (MutualFund)s.createCriteria(MutualFund.class)
			.add(Restrictions.between("holdings.amount", AMOUNT.subtract(one), AMOUNT.add(one)))
			.uniqueResult();
		assertEquals(AMOUNT.doubleValue(), f.getHoldings().getAmount().doubleValue(), 0.01d);
		
		// Test predicate and entity load via HQL
		f = (MutualFund)s.createQuery("from MutualFund f where f.holdings.amount between ? and ?")
			.setBigDecimal(0, AMOUNT.subtract(one))
			.setBigDecimal(1, AMOUNT.add(one))
			.uniqueResult();
		assertEquals(AMOUNT.doubleValue(), f.getHoldings().getAmount().doubleValue(), 0.01d);
				
		s.delete(f);
		t.commit();
		s.close();
		
	}
