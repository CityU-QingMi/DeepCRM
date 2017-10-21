    public static String getTriggerDDL(String trn, int typ, String tab,
                                       int qs,
                                       String impl) throws SQLException {

        StringBuffer sb = new StringBuffer();

        sb.append("CREATE TRIGGER ");
        sb.append(trn);
        sb.append(' ');
        sb.append(getWhenSpec(typ));
        sb.append(' ');
        sb.append(getOperationSpec(typ));
        sb.append(" ON ");
        sb.append(tab);
        sb.append(' ');
        sb.append(getForEachSpec(typ));
        sb.append(' ');
        sb.append(getQueueSpec(qs));
        sb.append(" CALL \"");
        sb.append(impl);
        sb.append("\"");

        return sb.toString();
    }
