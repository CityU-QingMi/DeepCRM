    public String getMessage() {

        String message = super.getMessage();

        if (appendages == null) {
            return message;
        }

        StringBuffer sb = new StringBuffer();

        if (message != null) {
            sb.append(message);
        }

        for (String appendage : appendages) {
            if (sb.length() > 0) {
                sb.append(LS);
            }

            sb.append(appendage);
        }

        return sb.toString();
    }
