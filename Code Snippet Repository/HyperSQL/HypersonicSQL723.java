    public boolean scanNull() {

        scanSeparator();

        int character = charAt(currentPosition);

        if (character == 'N' || character == 'n') {
            if (scanSpecialIdentifier(Tokens.T_NULL)) {
                return true;
            }
        }

        return false;
    }
