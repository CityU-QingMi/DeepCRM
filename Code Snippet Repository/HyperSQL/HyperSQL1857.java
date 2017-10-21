    public String describe(Session session) {

        StringBuffer sb = new StringBuffer();

        sb.append(super.toString()).append("[\n");
        sb.append("escapeChar=").append(escapeChar).append('\n');
        sb.append("isNull=").append(isNull).append('\n');

//        sb.append("optimised=").append(optimised).append('\n');
        sb.append("isIgnoreCase=").append(isIgnoreCase).append('\n');
        sb.append("iLen=").append(iLen).append('\n');
        sb.append("iFirstWildCard=").append(iFirstWildCard).append('\n');
        sb.append("cLike=");

        if (cLike != null) {
            sb.append(StringUtil.arrayToString(cLike));
        }

        sb.append('\n');
        sb.append("wildCardType=");

        if (wildCardType != null) {
            sb.append(StringUtil.arrayToString(wildCardType));
        }

        sb.append(']');

        return sb.toString();
    }
