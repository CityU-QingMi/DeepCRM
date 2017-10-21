    @Test
    public void testSplit()
    {
        assertThat(StringUtil.csvSplit(null),nullValue());
        assertThat(StringUtil.csvSplit(null),nullValue());
        
        assertThat(StringUtil.csvSplit(""),emptyArray());
        assertThat(StringUtil.csvSplit(" \t\n"),emptyArray());
        
        assertThat(StringUtil.csvSplit("aaa"),arrayContaining("aaa"));
        assertThat(StringUtil.csvSplit(" \taaa\n"),arrayContaining("aaa"));
        assertThat(StringUtil.csvSplit(" \ta\n"),arrayContaining("a"));
        assertThat(StringUtil.csvSplit(" \t\u1234\n"),arrayContaining("\u1234"));
        
        assertThat(StringUtil.csvSplit("aaa,bbb,ccc"),arrayContaining("aaa","bbb","ccc"));
        assertThat(StringUtil.csvSplit("aaa,,ccc"),arrayContaining("aaa","","ccc"));
        assertThat(StringUtil.csvSplit(",b b,"),arrayContaining("","b b"));
        assertThat(StringUtil.csvSplit(",,bbb,,"),arrayContaining("","","bbb",""));
        
        assertThat(StringUtil.csvSplit(" aaa, bbb, ccc"),arrayContaining("aaa","bbb","ccc"));
        assertThat(StringUtil.csvSplit("aaa,\t,ccc"),arrayContaining("aaa","","ccc"));
        assertThat(StringUtil.csvSplit("  ,  b b  ,   "),arrayContaining("","b b"));
        assertThat(StringUtil.csvSplit(" ,\n,bbb, , "),arrayContaining("","","bbb",""));
        
        assertThat(StringUtil.csvSplit("\"aaa\", \" b,\\\"\",\"\""),arrayContaining("aaa"," b,\"",""));
    }
