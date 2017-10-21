	@Test
	public void testHistoryOfTuple2() {
		PersonTuple tuple = getAuditReader().find( PersonTuple.class, tuple2Ver2.getPersonTupleId(), 2 );

		Assert.assertEquals( tuple2Ver1, tuple );
		Assert.assertEquals( tuple2Ver1.isHelloWorld(), tuple.isHelloWorld() );
		Assert.assertEquals( tuple2Ver1.getPersonA().getId(), tuple.getPersonA().getId() );
		Assert.assertEquals( tuple2Ver1.getPersonB().getId(), tuple.getPersonB().getId() );

		tuple = getAuditReader().find( PersonTuple.class, tuple2Ver2.getPersonTupleId(), 3 );

		Assert.assertEquals( tuple2Ver2, tuple );
		Assert.assertEquals( tuple2Ver2.isHelloWorld(), tuple.isHelloWorld() );
		Assert.assertEquals( tuple2Ver2.getPersonA().getId(), tuple.getPersonA().getId() );
		Assert.assertEquals( tuple2Ver2.getPersonB().getId(), tuple.getPersonB().getId() );
	}
