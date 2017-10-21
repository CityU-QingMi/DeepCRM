    public void testLocalhostOnlyWithIPs() {

        ServerAcl acl;

        for (int i = 0; i < aclPermitLocalhosts.length; i++) {
            acl = (ServerAcl) aclPermitLocalhosts[i];

            assertTrue(
                "Denying access from localhost with localhost-permit ACL",
                acl.permitAccess(localhostByAddr.getAddress()));
            assertFalse(
                "Permitting access from other host with localhost-permit ACL",
                acl.permitAccess(otherHostByAddr.getAddress()));
        }
    }
