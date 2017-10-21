    public boolean end(Writer writer, String body) {
        java.lang.Number number = findNumberName();

        if (number != null) {

            NumberFormat format = getNumberFormat();
            findCurrency(format);
            setNumberFormatParameters(format);
            setRoundingMode(format);

            String msg = format.format(number);
            try {
                if (getVar() == null) {
                    writer.write(msg);
                } else {
                    putInContext(msg);
                }
            } catch (IOException e) {
                LOG.error("Could not write out Number tag", e);
            }
        }
        return super.end(writer, "");
    }
