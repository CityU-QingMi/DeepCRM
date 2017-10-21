	@Test
	public void testReferencedColumnNameBelongsToEmbeddedIdOfReferencedEntity() throws Exception {
		Session session = openSession();
		Transaction tx = session.beginTransaction();

		Integer companyCode = 10;
		Integer mfgCode = 100;
		String contractNumber = "NSAR97841";
		ContractId contractId = new ContractId(companyCode, 12457l, 1);

		Manufacturer manufacturer = new Manufacturer(new ManufacturerId(
				companyCode, mfgCode), "FORD");

		Model model = new Model(new ModelId(companyCode, mfgCode, "FOCUS"),
				"FORD FOCUS");

		session.persist(manufacturer);
		session.persist(model);

		Contract contract = new Contract();
		contract.setId(contractId);
		contract.setContractNumber(contractNumber);
		contract.setManufacturer(manufacturer);
		contract.setModel(model);

		session.persist(contract);

		session.flush();
		session.clear();

		contract = (Contract) session.load(Contract.class, contractId);
		assertEquals("NSAR97841", contract.getContractNumber());
		assertEquals("FORD", contract.getManufacturer().getName());
		assertEquals("FORD FOCUS", contract.getModel().getName());

		tx.commit();
		session.close();
	}
