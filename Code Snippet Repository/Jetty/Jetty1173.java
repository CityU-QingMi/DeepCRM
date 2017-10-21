    private void assertHttpFieldsSame(String msg, HttpFields expected, HttpFields actual)
    {
        assertThat(msg + ".size", actual.size(), is(expected.size()));
        
        for (HttpField actualField : actual)
        {
            if ("DATE".equalsIgnoreCase(actualField.getName()))
            {
                // skip comparison on Date, as these values can often differ by 1 second
                // during testing.
                continue;
            }
            assertThat(msg + ".contains(" + actualField + ")",expected.contains(actualField),is(true));
        }
    }
