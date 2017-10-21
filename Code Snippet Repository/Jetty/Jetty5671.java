    @Test
    public void testUrlEncodedStream()
        throws Exception
    {
        String [][] charsets = new String[][]
        {
           {StringUtil.__UTF8,null,"%30"},
           {StringUtil.__ISO_8859_1,StringUtil.__ISO_8859_1,"%30"},
           {StringUtil.__UTF8,StringUtil.__UTF8,"%30"},
           {StringUtil.__UTF16,StringUtil.__UTF16,"%00%30"},
        };

        // Note: "%30" -> decode -> "0"

        for (int i=0;i<charsets.length;i++)
        {
            ByteArrayInputStream in = new ByteArrayInputStream(("name\n=value+"+charsets[i][2]+"&name1=&name2&n\u00e3me3=value+3").getBytes(charsets[i][0]));
            MultiMap<String> m = new MultiMap<>();
            UrlEncoded.decodeTo(in, m, charsets[i][1]==null?null:Charset.forName(charsets[i][1]),-1,-1);
            assertEquals(charsets[i][1]+" stream length",4,m.size());
            assertThat(charsets[i][1]+" stream name\\n",m.getString("name\n"),is("value 0"));
            assertThat(charsets[i][1]+" stream name1",m.getString("name1"),is(""));
            assertThat(charsets[i][1]+" stream name2",m.getString("name2"),is(""));
            assertThat(charsets[i][1]+" stream n\u00e3me3",m.getString("n\u00e3me3"),is("value 3"));
        }


        if (java.nio.charset.Charset.isSupported("Shift_JIS"))
        {
            ByteArrayInputStream in2 = new ByteArrayInputStream("name=%83e%83X%83g".getBytes(StandardCharsets.ISO_8859_1));
            MultiMap<String> m2 = new MultiMap<>();
            UrlEncoded.decodeTo(in2, m2, Charset.forName("Shift_JIS"),-1,-1);
            assertEquals("stream length",1,m2.size());
            assertEquals("stream name","\u30c6\u30b9\u30c8",m2.getString("name"));
        }
        else
            assertTrue("Charset Shift_JIS not supported by jvm", true);

    }
