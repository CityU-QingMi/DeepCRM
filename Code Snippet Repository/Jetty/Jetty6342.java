    private void assertServerOpenConnectionCount(int expectedCount)
    {
        Set<Session> sessions = serverContainer.getOpenSessions();
        int openCount = 0;
        for (Session session : sessions)
        {
            Assert.assertThat("Session.isopen: " + session, session.isOpen(), Matchers.is(true));
            openCount++;
        }
        Assert.assertThat("Open Session Count", openCount, Matchers.is(expectedCount));
    }
