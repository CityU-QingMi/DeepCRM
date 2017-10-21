    @Test
    public void testSetAttributesWithCorrectValues() throws Exception
    {
        // given
        attributes = getAttributes("fname","vijay");
        attributes = objectMBean.setAttributes(attributes);

        // when
        attributes = objectMBean.getAttributes(new String[]
        { "fname" });

        // then
        assertEquals("Fname value must be equal to vijay","vijay",((Attribute)(attributes.get(0))).getValue());
    }
