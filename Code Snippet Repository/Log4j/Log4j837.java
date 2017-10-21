        @Override
        public PosixViewAttributeAction build() {
            if (Strings.isEmpty(basePath)) {
                LOGGER.error("Posix file attribute view action not valid because base path is empty.");
                return null;
            }

            if (filePermissions == null && Strings.isEmpty(filePermissionsString)
                        && Strings.isEmpty(fileOwner) && Strings.isEmpty(fileGroup)) {
                LOGGER.error("Posix file attribute view not valid because nor permissions, user or group defined.");
                return null;
            }

            if (!FileUtils.isFilePosixAttributeViewSupported()) {
                LOGGER.warn("Posix file attribute view defined but it is not supported by this files system.");
                return null;
            }

            return new PosixViewAttributeAction(basePath, followLinks, maxDepth, pathConditions,
                    subst != null ? subst : configuration.getStrSubstitutor(),
                    filePermissions != null ? filePermissions :
                                filePermissionsString != null ? PosixFilePermissions.fromString(filePermissionsString) : null,
                    fileOwner,
                    fileGroup);
        }
