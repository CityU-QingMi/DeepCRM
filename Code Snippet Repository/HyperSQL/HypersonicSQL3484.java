    protected String getResultString() {

        StringBuffer sb = new StringBuffer();

        if (lines.length == 1 && lines[0].trim().length() < 1) {
            return "";
        }

        for (int i = 0; i < lines.length; i++) {
            if (i > 0) {
                sb.append(LS);
            }

            sb.append("+ " + lines[i]);
        }

        TestUtil.expandStamps(sb);

        return sb.toString().trim();
    }
