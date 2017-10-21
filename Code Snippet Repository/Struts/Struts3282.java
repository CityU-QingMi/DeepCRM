    protected Object number() throws JSONException {
        this.buf.setLength(0);
        boolean toDouble = false;

        if (this.c == '-') {
            this.add();
        }

        this.addDigits();

        if (this.c == '.') {
            toDouble = true;
            this.add();
            this.addDigits();
        }

        if ((this.c == 'e') || (this.c == 'E')) {
            toDouble = true;
            this.add();

            if ((this.c == '+') || (this.c == '-')) {
                this.add();
            }

            this.addDigits();
        }

        if (toDouble) {
            try {
                return Double.parseDouble(this.buf.toString());
            } catch (NumberFormatException e) {
                throw buildInvalidInputException();
            }
        } else {
            try {
                return Long.parseLong(this.buf.toString());
            } catch (NumberFormatException e) {
                throw buildInvalidInputException();
            }
        }
    }
