    public MimeMessageBuilder setFrom(final String from) throws MessagingException {
        final InternetAddress address = parseAddress(from);

        if (null != address) {
            message.setFrom(address);
        } else {
            try {
                message.setFrom();
            } catch (final Exception ex) {
                message.setFrom((InternetAddress) null);
            }
        }
        return this;
    }
