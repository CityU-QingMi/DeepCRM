    public void testNoLocalNetOnlyWithNames() {

        ServerAcl acl;

        for (int i = 0; i < aclDenyLocalNets.length; i++) {
            acl = (ServerAcl) aclDenyLocalNets[i];

            assertFalse(
                "Permitting access from localNet with localNet-deny ACL",
                acl.permitAccess(localhostByName.getAddress()));
        }
    }
