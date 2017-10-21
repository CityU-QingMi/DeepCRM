    public String getNameString() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_ROW);
        sb.append('(');

        for (int i = 0; i < dataTypes.length; i++) {
            if (i > 0) {
                sb.append(',');
            }

            sb.append(dataTypes[i].getDefinition());
        }

        sb.append(')');

        return sb.toString();
    }
