    public int compare(String a, String b) {

        int i;

        if (collator == null) {
            if (isUnicodeSimple) {
                i = a.compareTo(b);
            } else {
                i = a.compareToIgnoreCase(b);
            }
        } else {
            if (isUpperCaseCompare) {
                i = collator.compare(toUpperCase(a), toUpperCase(b));
            } else {
                i = collator.compare(a, b);
            }
        }

        return (i == 0) ? 0
                        : (i < 0 ? -1
                                 : 1);
    }
