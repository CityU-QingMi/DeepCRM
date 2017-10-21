    public void verifyGenericProperties(AbstractUITag tag, String theme, Map<String, PropertyHolder> propertiesToTest, String[] exclude) throws Exception {
        if (tag != null && propertiesToTest != null) {
            List excludeList;
            if (exclude != null) {
                excludeList = Arrays.asList(exclude);
            } else {
                excludeList = Collections.EMPTY_LIST;
            }

            tag.setPageContext(pageContext);
            if (theme != null) {
                tag.setTheme(theme);
            }

            for (PropertyHolder propertyHolder : propertiesToTest.values()) {
                if (!excludeList.contains(propertyHolder.getName())) {
                    BeanUtils.setProperty(tag, propertyHolder.getName(), propertyHolder.getValue());
                }
            }
            tag.doStartTag();
            tag.doEndTag();
            String writerString = normalize(writer.toString(), true);
            LOG.debug("AbstractUITagTest - [verifyGenericProperties]: Tag output is {}", writerString);

            assertFalse("Freemarker error detected in tag output: " + writerString, writerString.contains(normalize(FREEMARKER_ERROR_EXPECTATION)));

            for (PropertyHolder propertyHolder : propertiesToTest.values()) {
                if (!excludeList.contains(propertyHolder.getName())) {
                    assertTrue("Expected to find: " + propertyHolder.getExpectation() + " in resulting String: " + writerString, writerString.contains(propertyHolder.getExpectation()));
                }
            }
        }
    }
