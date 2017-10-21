    @Test
    public void testEmptyAttribute() throws Exception {
        final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
        logger.info("msg");

        final ListAppender app = (ListAppender) context.getRequiredAppender("List");
        assertNotNull("No ListAppender", app);

        final List<String> messages = app.getMessages();
        assertNotNull("No Messages", messages);
        assertEquals("message count" + messages, 1, messages.size());

        //<Property name="emptyElementKey" />
        //<Property name="emptyAttributeKey" value="" />
        //<Property name="emptyAttributeKey2" value=""></Property>
        //<Property name="elementKey">elementValue</Property>
        //<Property name="attributeKey" value="attributeValue" />
        //<Property name="attributeWithEmptyElementKey" value="attributeValue2"></Property>
        //<Property name="bothElementAndAttributeKey" value="attributeValue3">elementValue3</Property>
        final String expect = "1=elementValue" + // ${sys:elementKey}
                ",2=" + // ${sys:emptyElementKey}
                ",a=" + // ${sys:emptyAttributeKey}
                ",b=" + // ${sys:emptyAttributeKey2}
                ",3=attributeValue" + // ${sys:attributeKey}
                ",4=attributeValue2" + // ${sys:attributeWithEmptyElementKey}
                ",5=elementValue3,m=msg"; // ${sys:bothElementAndAttributeKey}
        assertEquals(expect, messages.get(0));
        app.clear();
    }
