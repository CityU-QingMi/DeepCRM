        @Override
        public InputSource resolveEntity(String publicId, String systemId) {
            if (dtdMappings != null && dtdMappings.containsKey(publicId)) {
                String dtdFile = dtdMappings.get(publicId);
                return new InputSource(ClassLoaderUtil.getResourceAsStream(dtdFile, DomHelper.class));
            } else {
                LOG.warn("Local DTD is missing for publicID: {} - defined mappings: {}", publicId, dtdMappings);
            }
            return null;
        }
