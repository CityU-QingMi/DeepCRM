    @Test
    public void testMessageFactorySetFrom() throws MessagingException {
        final MimeMessageBuilder builder = new MimeMessageBuilder(null);
        final String address = "testing@example.com";

        assertNull(builder.build().getFrom());

        builder.setFrom(null);
        Address[] array = null;
        final Address addr = InternetAddress.getLocalAddress(null);
        if (addr != null) {
            array = new Address[] { addr };
        }
        assertArrayEquals(array, builder.build().getFrom());

        builder.setFrom(address);
        assertArrayEquals(new Address[] { new InternetAddress(address) },
                builder.build().getFrom());
    }
