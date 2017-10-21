    public boolean equals(Object other) {

        if (other instanceof Xid) {
            Xid o = (Xid) other;

            return formatID == o.getFormatId()
                    && Arrays.equals(txID, o.getGlobalTransactionId())
                    && Arrays.equals(txBranch, o.getBranchQualifier());
        }

        return false;
    }
