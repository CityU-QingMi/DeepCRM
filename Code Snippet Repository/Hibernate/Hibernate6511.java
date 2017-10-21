	@Test
	public void testEmbeddedSuperclass() {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		VanillaSwap swap = new VanillaSwap();
		swap.setInstrumentId( "US345421" );
		swap.setCurrency( VanillaSwap.Currency.EUR );
		FixedLeg fixed = new FixedLeg();
		fixed.setPaymentFrequency( Leg.Frequency.SEMIANNUALLY );
		fixed.setRate( 5.6 );
		FloatLeg floating = new FloatLeg();
		floating.setPaymentFrequency( Leg.Frequency.QUARTERLY );
		floating.setRateIndex( FloatLeg.RateIndex.LIBOR );
		floating.setRateSpread( 1.1 );
		swap.setFixedLeg( fixed );
		swap.setFloatLeg( floating );
		s.persist( swap );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		swap = (VanillaSwap) s.get( VanillaSwap.class, swap.getInstrumentId() );
		// All fields must be filled with non-default values
		fixed = swap.getFixedLeg();
		assertNotNull( "Fixed leg retrieved as null", fixed );
		floating = swap.getFloatLeg();
		assertNotNull( "Floating leg retrieved as null", floating );
		assertEquals( Leg.Frequency.SEMIANNUALLY, fixed.getPaymentFrequency() );
		assertEquals( Leg.Frequency.QUARTERLY, floating.getPaymentFrequency() );
		s.delete( swap );
		tx.commit();
		s.close();
	}
