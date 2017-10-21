    public void testRenderCheckboxWithNameValue() {
        tag.setName("name_");
        tag.setValue("%{someValue}");
        tag.setDisabled("true");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<input type='checkbox' name='name_' value='true' checked='checked' id='name_'></input><input type='hidden' id='__checkbox_name_' name='__checkbox_name_' value='__checkbox_true'></input>");
        assertEquals(expected, output);
    }
