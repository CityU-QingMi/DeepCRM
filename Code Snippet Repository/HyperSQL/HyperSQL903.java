    public String toString() {

        StringBuffer sb = new StringBuffer(512);
        //
        sb.append("formatId=").append(getFormatId());
        //
        sb.append(" globalTransactionId(").append(txID.length).append(")={0x");
        for (int i = 0; i < txID.length; i++) {
            final int hexVal = txID[i] & 0xFF;
            if (hexVal < 0x10) {
                sb.append("0").append(Integer.toHexString(txID[i] & 0xFF));
            }
            sb.append(Integer.toHexString(txID[i] & 0xFF));
        }
        //
        sb.append("} branchQualifier(").append(txBranch.length).append("))={0x");
        for (int i = 0; i < txBranch.length; i++) {
            final int hexVal = txBranch[i] & 0xFF;
            if (hexVal < 0x10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(txBranch[i] & 0xFF));
        }
        sb.append("}");
        //
        return sb.toString();
    }
