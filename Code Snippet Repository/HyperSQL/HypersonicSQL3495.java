    protected String getTestResultString() {

        StringBuffer b = new StringBuffer();

        b.append(LS + "******" + LS);
        b.append("Type: ");
        b.append(getType()).append(LS);
        b.append("SQL: ").append(getSql()).append(LS);
        b.append("expected results:").append(LS);
        b.append(getResultString()).append(LS);

        //check to see if the message field has been populated
        if (getMessage() != null) {
            b.append(LS + "message:").append(LS);
            b.append(getMessage()).append(LS);
        }

        b.append("actual results:").append(LS);
        b.append(getActualResultString());
        b.append(LS + "******" + LS);

        return b.toString();
    }
