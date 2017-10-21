    public boolean permitAccess(byte[] addr) {

        ensureAclsUptodate();

        for (int i = 0; i < aclEntries.size(); i++) {
            if (((AclEntry) aclEntries.get(i)).matches(addr)) {
                AclEntry hit = (AclEntry) aclEntries.get(i);

                println("Addr '" + ServerAcl.dottedNotation(addr)
                        + "' matched rule #" + (i + 1) + ":  " + hit);

                return hit.allow;
            }
        }

        throw new RuntimeException("No rule matches address '"
                                   + ServerAcl.dottedNotation(addr) + "'");
    }
