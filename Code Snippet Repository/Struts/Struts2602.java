    protected Number getInteger() {
        if (this.number == null) {
            try {
                this.number = new Long(this.image);
            } catch (ArithmeticException e1) {
                this.number = new BigInteger(this.image);
            }
        }
        return number;
    }
