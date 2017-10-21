    private void findCurrency(NumberFormat format) {
        if (currency != null) {
            Object currencyValue = findValue(currency);
            if (currencyValue != null) {
                currency = currencyValue.toString();
            }
            try {
                format.setCurrency(Currency.getInstance(currency));
            } catch (IllegalArgumentException iae) {
                LOG.error("Could not recognise a currency of [" + currency + "]");
            }
        }
    }
