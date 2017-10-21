    @Override
    public boolean equals (Object obj) {
        if (this == obj)  return true;

        if (!(obj instanceof ExchangeRateKey)) return false;

        ExchangeRateKey q = (ExchangeRateKey) obj;
        return q.date == date && q.currency1 == this.currency1 && q.currency2 == this.currency2;

    }
