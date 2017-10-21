    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof FastDatePrinter == false) {
            return false;
        }
        final FastDatePrinter other = (FastDatePrinter) obj;
        return mPattern.equals(other.mPattern)
            && mTimeZone.equals(other.mTimeZone) 
            && mLocale.equals(other.mLocale);
    }
