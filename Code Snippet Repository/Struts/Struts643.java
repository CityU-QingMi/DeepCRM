    @Override
    public Object convertValue(Map<String, Object> context, Object target, Member member, String propertyName, Object value, Class toType) {
        if (File.class.equals(toType)) {
            LOG.debug("Converting {} into {}, consider switching to {} and do not access {} directly!",
                    File.class.getName(), UploadedFile.class.getName(), UploadedFile.class.getName(), File.class.getName());

            Object obj;
            if (value.getClass().isArray() && Array.getLength(value) == 1) {
                obj = Array.get(value, 0);
            } else {
                obj = value;
            }

            if (obj instanceof UploadedFile) {
                UploadedFile file = (UploadedFile) obj;
                if (file.getContent() instanceof File) {
                    return file.getContent();
                }
                return new File(file.getAbsolutePath());
            }
        }

        return super.convertValue(context, target, member, propertyName, value, toType);
    }
