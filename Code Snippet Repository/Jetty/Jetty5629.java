    @Test
    public void testHasControlCharacter()
    {
        assertThat(StringUtil.indexOfControlChars("\r\n"), is(0));
        assertThat(StringUtil.indexOfControlChars("\t"), is(0));
        assertThat(StringUtil.indexOfControlChars(";\n"), is(1));
        assertThat(StringUtil.indexOfControlChars("abc\fz"), is(3));
        assertThat(StringUtil.indexOfControlChars("z\010"), is(1));
        assertThat(StringUtil.indexOfControlChars(":\u001c"), is(1));

        assertThat(StringUtil.indexOfControlChars(null), is(-1));
        assertThat(StringUtil.indexOfControlChars(""), is(-1));
        assertThat(StringUtil.indexOfControlChars("   "), is(-1));
        assertThat(StringUtil.indexOfControlChars("a"), is(-1));
        assertThat(StringUtil.indexOfControlChars("."), is(-1));
        assertThat(StringUtil.indexOfControlChars(";"), is(-1));
        assertThat(StringUtil.indexOfControlChars("Euro is \u20ac"), is(-1));
    }
