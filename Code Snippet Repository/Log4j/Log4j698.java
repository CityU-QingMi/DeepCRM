        @SuppressWarnings({"", ""})
        @Override
        public SyslogAppender build() {
            final Protocol protocol = getProtocol();
            final SslConfiguration sslConfiguration = getSslConfiguration();
            final boolean useTlsMessageFormat = sslConfiguration != null || protocol == Protocol.SSL;
            final Configuration configuration = getConfiguration();
            Layout<? extends Serializable> layout = getLayout();
            if (layout == null) {
                layout = RFC5424.equalsIgnoreCase(format)
                        ? Rfc5424Layout.createLayout(facility, id, enterpriseNumber, includeMdc, mdcId, mdcPrefix,
                                eventPrefix, newLine, escapeNL, appName, msgId, excludes, includes, required,
                                exceptionPattern, useTlsMessageFormat, loggerFields, configuration)
                        :
                        // @formatter:off
                        SyslogLayout.newBuilder()
                            .setFacility(facility)
                            .setIncludeNewLine(newLine)
                            .setEscapeNL(escapeNL)
                            .setCharset(charsetName)
                            .build();
                        // @formatter:off
            }
            final String name = getName();
            if (name == null) {
                LOGGER.error("No name provided for SyslogAppender");
                return null;
            }
            final AbstractSocketManager manager = createSocketManager(name, protocol, getHost(), getPort(), getConnectTimeoutMillis(),
                    sslConfiguration, getReconnectDelayMillis(), getImmediateFail(), layout, Constants.ENCODER_BYTE_BUFFER_SIZE, null);

            return new SyslogAppender(name, layout, getFilter(), isIgnoreExceptions(), isImmediateFlush(), manager,
                    getAdvertise() ? configuration.getAdvertiser() : null);
        }
