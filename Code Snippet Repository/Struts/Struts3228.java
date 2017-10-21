    public void testRenderCheckbox() {
        tag.setName("name_");
        tag.setDisabled("true");
        tag.setTabindex("1");
        tag.setId("id_");
        tag.setCssClass("class");
        tag.setCssStyle("style");
        tag.setTitle("title");
        tag.setFieldValue("xyz");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<input type='checkbox' name='name_' value='xyz' tabindex='1' id='id_' class='class' style='style' title='title'></input><input type='hidden' id='__checkbox_id_' name='__checkbox_name_' value='__checkbox_xyz'></input>");
        assertEquals(expected, output);
    }
