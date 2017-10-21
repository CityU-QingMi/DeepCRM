    @Test
    public void test_Equality_Hash() {
        final DateParser[] parsers= {
            getInstance(yMdHmsSZ, NEW_YORK, Locale.US),
            getInstance(DMY_DOT, NEW_YORK, Locale.US),
            getInstance(YMD_SLASH, NEW_YORK, Locale.US),
            getInstance(MDY_DASH, NEW_YORK, Locale.US),
            getInstance(MDY_SLASH, NEW_YORK, Locale.US),
            getInstance(MDY_SLASH, REYKJAVIK, Locale.US),
            getInstance(MDY_SLASH, REYKJAVIK, SWEDEN)
        };

        final Map<DateParser,Integer> map= new HashMap<>();
        int i= 0;
        for(final DateParser parser:parsers) {
            map.put(parser, Integer.valueOf(i++));
        }

        i= 0;
        for(final DateParser parser:parsers) {
            assertEquals(i++, map.get(parser).intValue());
        }
    }
