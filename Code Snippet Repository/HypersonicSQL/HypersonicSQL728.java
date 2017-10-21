    int scanIntervalFraction(int decimalPrecision) {

        if (intervalPosition == intervalString.length()) {
            return 0;
        }

        if (intervalString.charAt(intervalPosition) != '.') {
            return 0;
        }

        intervalPosition++;

        int currentValue  = 0;
        int currentDigits = 0;

        for (; intervalPosition < intervalString.length(); ) {
            int character = intervalString.charAt(intervalPosition);

            if (character >= '0' && character <= '9') {
                int digit = character - '0';

                currentValue *= 10;
                currentValue += digit;

                intervalPosition++;
                currentDigits++;

                if (currentDigits == DTIType.maxFractionPrecision) {
                    break;
                }
            } else {
                break;
            }
        }

        fractionPrecision = currentDigits;
        currentValue      *= DTIType.nanoScaleFactors[currentDigits];
        currentValue = DTIType.normaliseFraction(currentValue,
                decimalPrecision);

        return currentValue;
    }
