    public static String getTriggerDescriptor(String trn, int typ,
            String tab) {

        StringBuffer sb = new StringBuffer();

        sb.append("TRIGGER : ");
        sb.append(trn);
        sb.append(' ');
        sb.append(getWhenSpec(typ));
        sb.append(' ');
        sb.append(getOperationSpec(typ));
        sb.append(" ON ");
        sb.append(tab);
        sb.append(' ');
        sb.append(getForEachSpec(typ));

        return sb.toString();
    }
