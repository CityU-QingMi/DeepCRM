	@Test
	public void setYetAnotherNestedProperties() {
		String doctorCompany = "";
		String lawyerCompany = "Dr. Sueem";
		TestBean target = new TestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.setPropertyValue("doctor.company", doctorCompany);
		accessor.setPropertyValue("lawyer.company", lawyerCompany);
		assertEquals(doctorCompany, target.getDoctor().getCompany());
		assertEquals(lawyerCompany, target.getLawyer().getCompany());
	}
