    public String getDefinition() {

        switch (typeCode) {

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                StringBuffer sb = new StringBuffer(16);

                sb.append(getNameString());
                sb.append('(');
                sb.append(precision);

                if (scale != 0) {
                    sb.append(',');
                    sb.append(scale);
                }

                sb.append(')');

                return sb.toString();

            default :
                return getNameString();
        }
    }
