    public void testNoLocalNetOnlyWithIPs() {

        ServerAcl acl;

        for (int i = 0; i < aclDenyLocalNets.length; i++) {
            acl = (ServerAcl) aclDenyLocalNets[i];

            assertFalse(
                "Permitting access from localNet with localNet-deny ACL",
                acl.permitAccess(localhostByAddr.getAddress()));
            assertTrue("Denying access from other Net with localNet-deny ACL",
                       acl.permitAccess(otherHostByAddr.getAddress()));
        }
    }
