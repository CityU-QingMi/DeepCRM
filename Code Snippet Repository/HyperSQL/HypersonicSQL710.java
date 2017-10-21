    public boolean scanSpecialIdentifier(String identifier) {

        int length = identifier.length();

        if (limit - currentPosition < length) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            int character = identifier.charAt(i);

            if (character == sqlString.charAt(currentPosition + i)) {
                continue;
            }

            if (character
                    == Character.toUpperCase(sqlString.charAt(currentPosition
                        + i))) {
                continue;
            }

            return false;
        }

        currentPosition += length;

        return true;
    }
