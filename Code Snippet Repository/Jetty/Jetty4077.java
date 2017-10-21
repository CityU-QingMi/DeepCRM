    private <S> void checkThat(S item, Matcher<S> matcher)
    {
        try
        {
            Assert.assertThat(item,matcher);
        }
        catch(Throwable th)
        {
            failures.add(th);
        }
    }
