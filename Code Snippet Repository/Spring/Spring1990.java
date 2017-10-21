	@Test
	public void testDeepCopyOfStringArrayTypedFieldsOnCopyCtor() throws Exception {

		SimpleMailMessage original = new SimpleMailMessage();
		original.setTo(new String[]{"fiona@mail.org", "apple@mail.org"});
		original.setCc(new String[]{"he@mail.org", "she@mail.org"});
		original.setBcc(new String[]{"us@mail.org", "them@mail.org"});

		SimpleMailMessage copy = new SimpleMailMessage(original);

		original.getTo()[0] = "mmm@mmm.org";
		original.getCc()[0] = "mmm@mmm.org";
		original.getBcc()[0] = "mmm@mmm.org";

		assertEquals("fiona@mail.org", copy.getTo()[0]);
		assertEquals("he@mail.org", copy.getCc()[0]);
		assertEquals("us@mail.org", copy.getBcc()[0]);
	}
