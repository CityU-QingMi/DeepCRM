    private void updateDaylightSavingTime() {
        Arrays.fill(dstOffsets, 0);
        final int ONE_HOUR = (int) TimeUnit.HOURS.toMillis(1);
        if (timeZone.getOffset(midnightToday) != timeZone.getOffset(midnightToday + 23 * ONE_HOUR)) {
            for (int i = 0; i < dstOffsets.length; i++) {
                final long time = midnightToday + i * ONE_HOUR;
                dstOffsets[i] = timeZone.getOffset(time) - timeZone.getRawOffset();
            }
            if (dstOffsets[0] > dstOffsets[23]) { // clock is moved backwards.
                // we obtain midnightTonight with Calendar.getInstance(TimeZone), so it already includes raw offset
                for (int i = dstOffsets.length - 1; i >= 0; i--) {
                    dstOffsets[i] -= dstOffsets[0]; //
                }
            }
        }
    }
