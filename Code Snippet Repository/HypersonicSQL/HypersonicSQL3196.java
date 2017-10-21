    public void testLocalhostOnlyWithNames() {

        ServerAcl acl;

        for (int i = 0; i < aclPermitLocalhosts.length; i++) {
            acl = (ServerAcl) aclPermitLocalhosts[i];

            assertTrue(
                "Denying access from localhost with localhost-permit ACL",
                acl.permitAccess(localhostByName.getAddress()));
        }
    }
