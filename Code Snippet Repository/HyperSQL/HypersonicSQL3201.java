    public void testLocalNetOnlyWithIPs() {

        ServerAcl acl;

        for (int i = 0; i < aclPermitLocalNets.length; i++) {
            acl = (ServerAcl) aclPermitLocalNets[i];

            assertTrue("Denying access from localNet with localNet-permit ACL",
                       acl.permitAccess(localhostByAddr.getAddress()));
            assertFalse(
                "Permitting access from other Net with localNet-permit ACL",
                acl.permitAccess(otherHostByAddr.getAddress()));
        }
    }
