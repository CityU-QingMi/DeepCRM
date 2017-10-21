    public Number getFloatingPoint() {
        if (this.number == null) {
            try {
                this.number = new Double(this.image);
            } catch (ArithmeticException e0) {
                this.number = new BigDecimal(this.image);
            }
        }
        return this.number;
    }
