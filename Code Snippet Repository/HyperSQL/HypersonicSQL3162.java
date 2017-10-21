    synchronized protected void ensureAclsUptodate() {

        if (lastLoadTime > aclFile.lastModified()) {
            return;
        }

        try {
            aclEntries = load();

            println("ACLs reloaded from file");

            return;
        } catch (Exception e) {
            println("Failed to reload ACL file.  Retaining old ACLs.  " + e);
        }
    }
