    public void testRenderDateTextField() {
    	tag.setId("id");
        tag.setName("name");
        tag.setFormat("yyyy-MM-dd");

        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        String output = writer.getBuffer().toString();
        String expected = s("<div id='id'>" +
        		"<input type='text' class='date_year' size='4' maxlength='4' id='__year_id' name='__year_name'></input>" +
        		"-<input type='text' class='date_month' size='2' maxlength='2' id='__month_id' name='__month_name'></input>" +
        		"-<input type='text' class='date_day' size='2' maxlength='2' id='__day_id' name='__day_name'></input></div>");
        assertEquals(expected, output);
    }
