    private static boolean hasUnprintable(String s) {

        for (int i = 0, len = s.length(); i < len; i++) {
            if (Character.isISOControl(s.charAt(i))) {
                return true;
            }
        }

        return false;
    }
