    public Map<Date, List<WeblogEntry>> getWeblogEntryObjectMap(WeblogEntrySearchCriteria wesc) throws WebloggerException {
        TreeMap<Date, List<WeblogEntry>> map = new TreeMap<Date, List<WeblogEntry>>(Collections.reverseOrder());

        List<WeblogEntry> entries = getWeblogEntries(wesc);

        Calendar cal = Calendar.getInstance();
        if (wesc.getWeblog() != null) {
            cal.setTimeZone(wesc.getWeblog().getTimeZoneInstance());
        }

        for (WeblogEntry entry : entries) {
            Date sDate = DateUtil.getNoonOfDay(entry.getPubTime(), cal);
            List<WeblogEntry> dayEntries = map.get(sDate);
            if (dayEntries == null) {
                dayEntries = new ArrayList<WeblogEntry>();
                map.put(sDate, dayEntries);
            }
            dayEntries.add(entry);
        }
        return map;
    }
