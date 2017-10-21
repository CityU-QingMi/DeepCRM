    private void checkParsePosition(final String formattedDate) {
        final SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setTimeZone(timeZone);
        final DateParser fdf = new FastDateParser(format, timeZone, locale);

        final ParsePosition sdfP = new ParsePosition(0);
        final Date expectedTime = sdf.parse(formattedDate, sdfP);
        final int sdferrorIndex = sdfP.getErrorIndex();
        if (valid) {
            assertEquals("Expected SDF error index -1 ", -1, sdferrorIndex);
            final int endIndex = sdfP.getIndex();
            final int length = formattedDate.length();
            if (endIndex != length) {
                // Error in test data
                throw new RuntimeException("Test data error: expected SDF parse to consume entire string; endindex " + endIndex + " != " + length);                
            }
        } else {
            final int errorIndex = sdfP.getErrorIndex();
            if (errorIndex == -1) {
                throw new RuntimeException("Test data error: expected SDF parse to fail, but got " + expectedTime);                
            }
        }

        final ParsePosition fdfP = new ParsePosition(0);
        final Date actualTime = fdf.parse(formattedDate, fdfP);
        final int fdferrorIndex = fdfP.getErrorIndex();
        if (valid) {
            assertEquals("Expected FDF error index -1 ", -1, fdferrorIndex);
            final int endIndex = fdfP.getIndex();
            final int length = formattedDate.length();
            assertEquals("Expected FDF to parse full string " + fdfP, length, endIndex);
            assertEquals(locale.toString()+" "+formattedDate +"\n", expectedTime, actualTime);
        } else {
            assertNotEquals("Test data error: expected FDF parse to fail, but got " + actualTime, -1, fdferrorIndex);
            assertTrue("FDF error index ("+ fdferrorIndex + ") should approxiamate SDF index (" + sdferrorIndex + ")",
                    sdferrorIndex - fdferrorIndex <= 4);
        }        
    }
