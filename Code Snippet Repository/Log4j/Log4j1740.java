    @Test
    public void testMessageFactorySetSubject() throws MessagingException {
        final MimeMessageBuilder builder = new MimeMessageBuilder(null);
        final String subject = "Test Subject";

        assertNull(builder.build().getSubject());

        builder.setSubject(null);
        assertNull(builder.build().getSubject());

        builder.setSubject(subject);
        assertEquals(subject, builder.build().getSubject());
    }
