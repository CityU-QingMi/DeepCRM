	@Test
	public void equalsVerification() {
		PeriodicTrigger trigger1 = new PeriodicTrigger(3000);
		PeriodicTrigger trigger2 = new PeriodicTrigger(3000);
		assertFalse(trigger1.equals(new String("not a trigger")));
		assertFalse(trigger1.equals(null));
		assertEquals(trigger1, trigger1);
		assertEquals(trigger2, trigger2);
		assertEquals(trigger1, trigger2);
		trigger2.setInitialDelay(1234);
		assertFalse(trigger1.equals(trigger2));
		assertFalse(trigger2.equals(trigger1));
		trigger1.setInitialDelay(1234);
		assertEquals(trigger1, trigger2);
		trigger2.setFixedRate(true);
		assertFalse(trigger1.equals(trigger2));
		assertFalse(trigger2.equals(trigger1));
		trigger1.setFixedRate(true);
		assertEquals(trigger1, trigger2);
		PeriodicTrigger trigger3 = new PeriodicTrigger(3, TimeUnit.SECONDS);
		trigger3.setInitialDelay(7);
		trigger3.setFixedRate(true);
		assertFalse(trigger1.equals(trigger3));
		assertFalse(trigger3.equals(trigger1));
		trigger1.setInitialDelay(7000);
		assertEquals(trigger1, trigger3);
	}
