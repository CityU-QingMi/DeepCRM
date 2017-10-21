    public static byte[] parseBase64Binary(final String encoded) {
        if (method == null) {
            LOGGER.error("No base64 converter");
        } else {
            try {
                return (byte[]) method.invoke(decoder, encoded);
            } catch (final IllegalAccessException ex) {
                LOGGER.error("Error decoding string - " + ex.getMessage());
            } catch (final InvocationTargetException ex) {
                LOGGER.error("Error decoding string - " + ex.getMessage());
            }
        }
        return new byte[0];
    }
