    public void testNoLocalhostOnlyWithNames() {

        ServerAcl acl;

        for (int i = 0; i < aclDenyLocalhosts.length; i++) {
            acl = (ServerAcl) aclDenyLocalhosts[i];

            assertFalse(
                "Permitting access from localhost with localhost-deny ACL",
                acl.permitAccess(localhostByName.getAddress()));
        }
    }
