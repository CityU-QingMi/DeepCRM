    @Test
    public void testMessageFactorySetRecipients() throws MessagingException {
        final MimeMessageBuilder builder = new MimeMessageBuilder(null);
        final String addresses = "testing1@example.com,testing2@example.com";

        assertNull(builder.build().getRecipients(
                Message.RecipientType.TO));

        builder.setRecipients(Message.RecipientType.TO, null);
        assertNull(builder.build().getRecipients(
                Message.RecipientType.TO));

        builder.setRecipients(Message.RecipientType.TO, addresses);
        assertArrayEquals(InternetAddress.parse(addresses), builder
                .build().getRecipients(Message.RecipientType.TO));
    }
