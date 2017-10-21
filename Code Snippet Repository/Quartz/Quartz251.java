    @Override
    public String toString() {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance();
        numberFormatter.setMaximumFractionDigits(0);
        numberFormatter.setMinimumIntegerDigits(2);
        StringBuffer buffer = new StringBuffer();
        buffer.append("base calendar: [");
        if (getBaseCalendar() != null) {
            buffer.append(getBaseCalendar().toString());
        } else {
            buffer.append("null");
        }
        buffer.append("], time range: '");
        buffer.append(numberFormatter.format(rangeStartingHourOfDay));
        buffer.append(":");
        buffer.append(numberFormatter.format(rangeStartingMinute));
        buffer.append(":");
        buffer.append(numberFormatter.format(rangeStartingSecond));
        buffer.append(":");
        numberFormatter.setMinimumIntegerDigits(3);
        buffer.append(numberFormatter.format(rangeStartingMillis));
        numberFormatter.setMinimumIntegerDigits(2);
        buffer.append(" - ");
        buffer.append(numberFormatter.format(rangeEndingHourOfDay));
        buffer.append(":");
        buffer.append(numberFormatter.format(rangeEndingMinute));
        buffer.append(":");
        buffer.append(numberFormatter.format(rangeEndingSecond));
        buffer.append(":");
        numberFormatter.setMinimumIntegerDigits(3);
        buffer.append(numberFormatter.format(rangeEndingMillis));
        buffer.append("', inverted: " + invertTimeRange + "]");
        return buffer.toString();
    }
