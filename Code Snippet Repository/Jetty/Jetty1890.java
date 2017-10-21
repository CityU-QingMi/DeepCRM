    @Test
    public void testSetAttributeWithCorrectAttrName() throws Exception
    {
        // given
        setUpGetAttribute("fname","charu");

        // when
        value = (String)objectMBean.getAttribute("fname");

        // then
        assertEquals("Attribute(fname) value must be equl to charu","charu",value);
    }
