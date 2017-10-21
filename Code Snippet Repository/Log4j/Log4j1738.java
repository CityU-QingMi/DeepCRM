    @Test
    public void testMessageFactorySetReplyTo() throws MessagingException {
        final MimeMessageBuilder builder = new MimeMessageBuilder(null);
        final String addresses = "testing1@example.com,testing2@example.com";

        assertNull(builder.build().getReplyTo());

        builder.setReplyTo(null);
        assertNull(builder.build().getReplyTo());

        builder.setReplyTo(addresses);
        assertArrayEquals(InternetAddress.parse(addresses), builder
                .build().getReplyTo());
    }
