    public void testDenyAllWithNames() {

        ServerAcl acl;

        for (int i = 0; i < aclDenyAlls.length; i++) {
            acl = (ServerAcl) aclDenyAlls[i];

            assertFalse("Permitting access from localhost with deny-all ACL",
                        acl.permitAccess(localhostByName.getAddress()));
        }
    }
