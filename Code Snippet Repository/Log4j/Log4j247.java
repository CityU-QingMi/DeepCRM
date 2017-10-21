    public static boolean equalsIgnoreCase(final CharSequence left, final int leftOffset, final int leftLength,
                                              final CharSequence right, final int rightOffset, final int rightLength) {
        if (leftLength == rightLength) {
            for (int i = 0; i < rightLength; i++) {
                if (toLowerCase(left.charAt(i + leftOffset)) != toLowerCase(right.charAt(i + rightOffset))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
