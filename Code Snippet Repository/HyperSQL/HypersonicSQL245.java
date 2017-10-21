    public static long getTruncatedPart(long m, int part) {

        synchronized (tempCalGMT) {
            tempCalGMT.setTimeInMillis(m);

            switch (part) {

                case DTIType.WEEK_OF_YEAR : {
                    int dayWeek = tempCalGMT.get(Calendar.DAY_OF_WEEK);

                    tempCalGMT.add(Calendar.DAY_OF_YEAR, 1 - dayWeek);
                    resetToDate(tempCalGMT);

                    break;
                }
                default : {
                    zeroFromPart(tempCalGMT, part);

                    break;
                }
            }

            return tempCalGMT.getTimeInMillis();
        }
    }
