        private void cancelOnWindows() {
            final SecurityManager currentSecurityManager = System.getSecurityManager();
            try {
                final SecurityManager securityManager = new SecurityManager() {
                    @Override
                    public void checkPermission(final Permission permission) {
                        final String permissionName = permission.getName();
                        if (permissionName != null && permissionName.startsWith("exitVM")) {
                            throw new SecurityException("test");
                        }
                    }
                };
                System.setSecurityManager(securityManager);
                daemon.stop();
            } catch (final SecurityException ex) {
                // ignore
            } finally {
                System.setSecurityManager(currentSecurityManager);
            }
        }
