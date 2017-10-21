    public void testCustomManager() {
        beginAt("/freemarker/customFreemarkerManagerDemo.action");

        String date = getElementTextByXPath("//*[@id='todaysDate']");
        assertNotNull(date);
        assertTrue(date.length() > 0);

        String time = getElementTextByXPath("//*[@id='timeNow']");
        assertNotNull(time);
        assertTrue(time.length() > 0);
    }
