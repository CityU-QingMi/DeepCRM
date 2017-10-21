    public int compare(Object val1, Object val2) {
        WeblogEntryWrapper entry1 = (WeblogEntryWrapper)val1;
        WeblogEntryWrapper entry2 = (WeblogEntryWrapper)val2;
        long pubTime1 = entry1.getPubTime().getTime();
        long pubTime2 = entry2.getPubTime().getTime();

        if (pubTime1 > pubTime2) {
            return -1;
        }
        else if (pubTime1 < pubTime2) {
            return 1;
        }

        // if pubTimes are the same, return results of String.compareTo() on Title
        return entry1.getTitle().compareTo(entry2.getTitle());
    }
