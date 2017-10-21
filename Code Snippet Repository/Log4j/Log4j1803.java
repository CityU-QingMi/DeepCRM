    @Test
    public void testConvert01() {
        final Message message = LOGGER.getMessageFactory().newMessage("Message #{} said [{}].", 3, "Hello");

        final String converted = this.converter.convertToDatabaseColumn(message);

        assertNotNull("The converted value should not be null.", converted);
        assertEquals("The converted value is not correct.", "Message #3 said [Hello].", converted);

        final Message reversed = this.converter.convertToEntityAttribute(converted);

        assertNotNull("The reversed value should not be null.", reversed);
        assertEquals("The reversed value is not correct.", "Message #3 said [Hello].", reversed.getFormattedMessage());
    }
