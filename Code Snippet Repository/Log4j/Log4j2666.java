        private SimpleEvent createEvent(final DatabaseEntry data) {
            final SimpleEvent event = new SimpleEvent();
            try {
                byte[] eventData = data.getData();
                if (secretKey != null) {
                    final Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                    eventData = cipher.doFinal(eventData);
                }
                final ByteArrayInputStream bais = new ByteArrayInputStream(eventData);
                final DataInputStream dais = new DataInputStream(bais);
                int length = dais.readInt();
                final byte[] bytes = new byte[length];
                dais.read(bytes, 0, length);
                event.setBody(bytes);
                length = dais.readInt();
                final Map<String, String> map = new HashMap<>(length);
                for (int i = 0; i < length; ++i) {
                    final String headerKey = dais.readUTF();
                    final String value = dais.readUTF();
                    map.put(headerKey, value);
                }
                event.setHeaders(map);
                return event;
            } catch (final Exception ex) {
                LOGGER.error("Error retrieving event", ex);
                return null;
            }
        }
