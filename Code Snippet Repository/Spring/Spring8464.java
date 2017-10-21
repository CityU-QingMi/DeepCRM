	@Test
	public void saveJuergenWithDriversLicense() {
		DriversLicense driversLicense = new DriversLicense(2L, 2222L);
		Person juergen = new Person(JUERGEN, driversLicense);
		int numRows = countRowsInPersonTable();
		personService.save(juergen);
		assertPersonCount(numRows + 1);
		assertNotNull("Should be able to save and retrieve Juergen", personService.findByName(JUERGEN));
		assertNotNull("Juergen's ID should have been set", juergen.getId());
	}
