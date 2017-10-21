    public static void defineFilePosixAttributeView(final Path path,
            final Set<PosixFilePermission> filePermissions,
            final String fileOwner,
            final String fileGroup) throws IOException {
        final PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        if (view != null) {
            final UserPrincipalLookupService lookupService = FileSystems.getDefault()
                    .getUserPrincipalLookupService();
            if (fileOwner != null) {
                final UserPrincipal userPrincipal = lookupService.lookupPrincipalByName(fileOwner);
                if (userPrincipal != null) {
                    // If not sudoers member, it will throw Operation not permitted
                    // Only processes with an effective user ID equal to the user ID
                    // of the file or with appropriate privileges may change the ownership of a file.
                    // If _POSIX_CHOWN_RESTRICTED is in effect for path
                    view.setOwner(userPrincipal);
                }
            }
            if (fileGroup != null) {
                final GroupPrincipal groupPrincipal = lookupService.lookupPrincipalByGroupName(fileGroup);
                if (groupPrincipal != null) {
                    // The current user id should be members of this group,
                    // if not will raise Operation not permitted
                    view.setGroup(groupPrincipal);
                }
            }
            if (filePermissions != null) {
                view.setPermissions(filePermissions);
            }
        }
    }
