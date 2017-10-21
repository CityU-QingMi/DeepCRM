    @Test
    public void testMutation()
    {
        QuotedCSV values = new QuotedCSV(false)
        {

            @Override
            protected void parsedValue(StringBuffer buffer)
            {
                if (buffer.toString().contains("DELETE"))
                {
                    String s = buffer.toString().replace("DELETE","");
                    buffer.setLength(0);
                    buffer.append(s);
                }
                if (buffer.toString().contains("APPEND"))
                {
                    String s = buffer.toString().replace("APPEND","Append")+"!";
                    buffer.setLength(0);
                    buffer.append(s);
                }
            }

            @Override
            protected void parsedParam(StringBuffer buffer, int valueLength, int paramName, int paramValue)
            {
                String name = paramValue>0?buffer.substring(paramName,paramValue-1):buffer.substring(paramName);
                if ("IGNORE".equals(name))
                    buffer.setLength(paramName-1);
            }
            
        };
            
        values.addValue("normal;param=val, testAPPENDandDELETEvalue ; n=v; IGNORE = this; x=y ");
        assertThat(values,Matchers.contains(
                "normal;param=val",
                "testAppendandvalue!;n=v;x=y"));
    }
