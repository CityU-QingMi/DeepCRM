    public String getClientPropertiesAsString() {

        if (isPropertyTrue(jdbc_translate_tti_types)) {
            StringBuffer sb = new StringBuffer(jdbc_translate_tti_types);

            sb.append('=').append(true);

            return sb.toString();
        }

        return "";
    }
