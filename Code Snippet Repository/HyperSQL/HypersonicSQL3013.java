    public void setSource(String text, long pos, int byteSize) {

        super.setSource(text, pos, byteSize);

        charLength = text.length();

        for (int i = charLength - 1; i > -1; i--) {
            if (text.charAt(i) == TextFileSettings.CR_CHAR
                    || text.charAt(i) == TextFileSettings.LF_CHAR) {
                charLength--;
            } else {
                break;
            }
        }
    }
